package com.accenture.flowershop.fe.Servlets;

import com.accenture.flowershop.be.BusinessService.Flower.FlowerBusinessService;
import com.accenture.flowershop.be.BusinessService.Utils.ServiceException;
import com.accenture.flowershop.fe.dto.FlowerDto;
import com.accenture.flowershop.fe.dto.OrderDto;
import com.accenture.flowershop.fe.dto.OrderPosDto;
import com.accenture.flowershop.fe.enums.SessionAttribute;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/service/addToBasket")
public class AddToBascket extends HttpServlet {

    @Autowired
    Mapper mapper;
    @Autowired
    private FlowerBusinessService flowerBusinessService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HttpSession session = req.getSession(false);
        OrderDto orderDto = (OrderDto) session.getAttribute(SessionAttribute.BASKET.toString());
        String idFlower = req.getParameter("idFlower");
        String quantity = req.getParameter("quantity");
        try {

            orderDto = addOrderPosition(orderDto, Long.parseLong(idFlower), Long.parseLong(quantity));
            /*Считаем общую стоимость корзины с учетом скидки*/
            orderDto.computePrice();

            session.setAttribute(SessionAttribute.BASKET.toString(), orderDto);
            req.setAttribute("bskt_msg", "Item is added.");

        }catch (NumberFormatException e){
            req.setAttribute("ctlg_err", ServiceException.ERROR_INVALIDATE_DATA);
        }
        catch (ServiceException e) {
            req.setAttribute("ctlg_err", e.getMessage());
        } finally {
            req.getRequestDispatcher("/service/mainpage").forward(req, resp);
        }
    }

    public OrderDto addOrderPosition(OrderDto orderDto, Long idFlower, Long quantity) throws ServiceException {
        if(quantity <= 0){
            throw new ServiceException(ServiceException.ERROR_INVALIDATE_DATA);
        }
        if (quantity > flowerBusinessService.getFlowerById(idFlower).getNumber()) {
            throw new ServiceException(ServiceException.ERROR_FLOWERSTOCK);
        }
        for (OrderPosDto orderPositionDto : orderDto.getOrderPositions()) {
            /*Если позиция уже существует, то увеличиваем её параметры*/
            if (idFlower.equals(orderPositionDto.getFlower().getIdFlower())) {
                Long sumQty = orderPositionDto.getNumber() + quantity;
                if (sumQty > orderPositionDto.getFlower().getNumber()) {
                    throw new ServiceException(ServiceException.ERROR_FLOWERSTOCK);
                }
                orderPositionDto.setOrder(orderDto);
                orderPositionDto.setNumber(sumQty);
                orderPositionDto.computePrice();
                return orderDto;
            }
        }
        /*Если похожей позиции не было, то добавляем её*/
        OrderPosDto newOrderPositionDto = new OrderPosDto(
                orderDto, mapper.map(flowerBusinessService.getFlowerById(idFlower), FlowerDto.class), quantity);
        orderDto.getOrderPositions().add(newOrderPositionDto);

        return orderDto;
    }

}
