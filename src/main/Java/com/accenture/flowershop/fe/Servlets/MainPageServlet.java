package com.accenture.flowershop.fe.Servlets;

import com.accenture.flowershop.be.BusinessService.Flower.FlowerBusinessService;
import com.accenture.flowershop.be.BusinessService.Order.OrderBusinessService;
import com.accenture.flowershop.be.BusinessService.User.UserBusinessService;
import com.accenture.flowershop.be.BusinessService.Utils.FlowerFilter;
import com.accenture.flowershop.be.BusinessService.Utils.Mapper;
import com.accenture.flowershop.be.BusinessService.Utils.ServiceException;
import com.accenture.flowershop.fe.dto.FlowerDto;
import com.accenture.flowershop.fe.dto.OrderDto;
import com.accenture.flowershop.fe.dto.UserDto;
import com.accenture.flowershop.fe.enums.SessionAttribute;
import com.accenture.flowershop.fe.enums.UserType;
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
import java.util.List;

@Service
@WebServlet
public class MainPageServlet extends HttpServlet {

    @Autowired
    Mapper mapper;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        UserDto userDto = (UserDto) session.getAttribute(SessionAttribute.USER.toString());
        session.setAttribute("balance", userDto.getBalance());
        session.setAttribute("discount", userDto.getDiscount());
        try {
            userDto = mapper.map(userBusinessService.getUserById(userDto.getIdUser()));


            session.setAttribute(SessionAttribute.USER.toString(), userDto);



            if (session.getAttribute(SessionAttribute.BASKET.toString()) == null) {
                OrderDto basket = new OrderDto();
                basket.setUser(userDto);
                session.setAttribute(SessionAttribute.BASKET.toString(), basket);
            }

            List<OrderDto> ordersDto = mapper.mapOrders(orderBusinessService.getAllOrders(mapper.map(userDto)));
            session.setAttribute(SessionAttribute.ORDERS.toString(), ordersDto);

            FlowerFilter filter = (FlowerFilter) request.getAttribute(SessionAttribute.FILTER.toString());
            List<FlowerDto> flowersDto;
            if (filter == null) {
                filter = new FlowerFilter();
                request.setAttribute(SessionAttribute.FILTER.toString(), filter);
                flowersDto = mapper.mapFlowers(flowerBusinessService.searchFilter(filter));
                request.setAttribute(SessionAttribute.FLOWERS.toString(), flowersDto);
            }


            if (userDto.getRole() == UserType.USER) {
                request.getRequestDispatcher("/WEB-INF/lib/mainPage.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/WEB-INF/lib/AdminMainPage.jsp").forward(request, response);
            }
        } catch (ServiceException e) {
            request.setAttribute("err", e.getMessage());
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/MainPageServlet").forward(request, response);
    }
}

