package com.accenture.flowershop.fe.Servlets;

import com.accenture.flowershop.be.BusinessService.Flower.FlowerBusinessService;
import com.accenture.flowershop.be.BusinessService.Order.OrderBusinessService;
import com.accenture.flowershop.be.BusinessService.User.UserBusinessService;
import com.accenture.flowershop.be.BusinessService.Utils.FlowerFilter;
import com.accenture.flowershop.be.BusinessService.Utils.MapperService;
import com.accenture.flowershop.be.BusinessService.Utils.ServiceException;
import com.accenture.flowershop.be.Entity.User.User;
import com.accenture.flowershop.fe.dto.FlowerDto;
import com.accenture.flowershop.fe.dto.OrderDto;
import com.accenture.flowershop.fe.dto.UserDto;
import com.accenture.flowershop.fe.enums.SessionAttribute;
import com.accenture.flowershop.fe.enums.UserType;
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
import java.util.List;

@WebServlet(urlPatterns = "/service/mainpage")
public class MainPageServlet extends HttpServlet {

    @Autowired
    MapperService mapper;
    @Autowired
    private UserBusinessService userBusinessService;
    @Autowired
    private FlowerBusinessService flowerBusinessService;
    @Autowired
    private OrderBusinessService orderBusinessService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        UserDto userDto = (UserDto) session.getAttribute(SessionAttribute.USER.toString());

        try {
            userDto = mapper.map(userBusinessService.getUserById(userDto.getIdUser()), UserDto.class);

            session.setAttribute(SessionAttribute.USER.toString(), userDto);

            if (session.getAttribute(SessionAttribute.BASKET.toString()) == null) {
                OrderDto basket = new OrderDto();
                basket.setUser(userDto);
                session.setAttribute(SessionAttribute.BASKET.toString(), basket);
            }

            List<OrderDto> ordersDto = mapper.mapList(orderBusinessService.getAllOrders(mapper.map(userDto, User.class)), OrderDto.class);
            req.setAttribute(SessionAttribute.ORDERS.toString(), ordersDto);

            FlowerFilter filter = (FlowerFilter) req.getAttribute(SessionAttribute.FILTER.toString());
            List<FlowerDto> flowersDto;
            if (filter == null) {
                filter = new FlowerFilter("", "", "");
                req.setAttribute(SessionAttribute.FILTER.toString(), filter);
            }
            flowersDto = mapper.mapList(flowerBusinessService.searchFilter(filter), FlowerDto.class);
            req.setAttribute(SessionAttribute.FLOWERS.toString(), flowersDto);

            if (userDto.getRole() == UserType.USER) {
                req.getRequestDispatcher("/mainPage.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/adminMainPage.jsp").forward(req, resp);
            }
        } catch (ServiceException e) {
            req.setAttribute("err", e.getMessage());
        }
    }
}

