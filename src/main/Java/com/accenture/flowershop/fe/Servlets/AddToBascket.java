package com.accenture.flowershop.fe.Servlets;

import com.accenture.flowershop.be.BusinessService.Flower.FlowerBusinessService;
import com.accenture.flowershop.be.BusinessService.Order.OrderBusinessService;
import com.accenture.flowershop.be.BusinessService.Utils.Mapper;
import com.accenture.flowershop.be.BusinessService.Utils.ServiceException;
import com.accenture.flowershop.fe.dto.FlowerDto;
import com.accenture.flowershop.fe.dto.OrderDto;
import com.accenture.flowershop.fe.dto.OrderPosDto;
import com.accenture.flowershop.fe.enums.SessionAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@WebServlet
public class AddToBascket extends HttpServlet {

    @Autowired
    OrderBusinessService orderBusinessService;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);//false
        OrderDto orderDto = (OrderDto) session.getAttribute(SessionAttribute.BASKET.toString());
        String idFlower = request.getParameter("idFlower");
        String number = request.getParameter("number");
        try {

            orderDto = addOrderPosition(orderDto, Long.parseLong(idFlower), Long.parseLong(number));
            /*Считаем общую стоимость корзины с учетом скидки*/
            orderDto.computePrice();

            session.setAttribute(SessionAttribute.BASKET.toString(), orderDto);


        }catch (NumberFormatException e){
            System.out.println("neee");
        }
        catch (ServiceException e) {
            System.out.println("ne ne ne");
        } finally {
            request.getRequestDispatcher("/MainPageServlet").forward(request, response);
        }
    }

    public OrderDto addOrderPosition(OrderDto orderDto, Long idFlower, Long number) throws ServiceException {
        if(number <= 0 || number == null){////////////////
            throw new ServiceException(ServiceException.ERROR_INVALIDATE_DATA);
        }
        if (number > flowerBusinessService.getFlowerById(idFlower).getNumber()) {
            throw new ServiceException(ServiceException.ERROR_FLOWERSTOCK);
        }
        if (orderDto.getOrderPositions() != null) {
            for (OrderPosDto orderPositionDto : orderDto.getOrderPositions()) {
                /*Если позиция уже существует, то увеличиваем её параметры*/
                if (idFlower.equals(orderPositionDto.getFlower().getIdFlower())) {
                    Long sumQty = orderPositionDto.getNumber() + number;
                    if (sumQty > orderPositionDto.getFlower().getNumber()) {
                        throw new ServiceException(ServiceException.ERROR_FLOWERSTOCK);
                    }
                    orderPositionDto.setOrder(orderDto);
                    orderPositionDto.setNumber(sumQty);
                    orderBusinessService.computePrice(orderPositionDto);
                    return orderDto;
                }
            }
        }
        /*Если похожей позиции не было, то добавляем её*/
        OrderPosDto newOrderPositionDto = new OrderPosDto(
                orderDto, mapper.map(flowerBusinessService.getFlowerById(idFlower)), number);
        newOrderPositionDto.setPrice(newOrderPositionDto.getFlower().getPrice().multiply(BigDecimal.valueOf(number)));
        orderDto.getOrderPositions().add(newOrderPositionDto);
        return orderDto;
    }

}
