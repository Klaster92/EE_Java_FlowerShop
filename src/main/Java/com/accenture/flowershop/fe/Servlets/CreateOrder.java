package com.accenture.flowershop.fe.Servlets;

import com.accenture.flowershop.be.BusinessService.Order.OrderBusinessService;
import com.accenture.flowershop.be.BusinessService.Utils.Mapper;
import com.accenture.flowershop.be.BusinessService.Utils.ServiceException;
import com.accenture.flowershop.fe.dto.OrderDto;
import com.accenture.flowershop.fe.dto.UserDto;
import com.accenture.flowershop.fe.enums.OrderStatus;
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
import java.util.ArrayList;
import java.util.List;

@Service
@WebServlet
public class CreateOrder extends HttpServlet {

    @Autowired
    Mapper mapper;

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

        HttpSession session = request.getSession(false);
        OrderDto orderDTO = (OrderDto) session.getAttribute(SessionAttribute.BASKET.toString());

        orderBusinessService.saveOrder(Mapper.map(orderDTO));

        //UserDto userDTO = (UserDto) session.getAttribute(SessionAttribute.USER.toString());

        List<OrderDto> dtoList = (List<OrderDto>) session.getAttribute(SessionAttribute.ORDERS.toString());
        List<OrderDto> dtoForSession = new ArrayList<>();
        for (OrderDto dto : dtoList) {
            if (!dto.getStatus().equals(OrderStatus.PAID)) {
                dtoForSession.add(dto);
            }
        }
        session.setAttribute(SessionAttribute.ORDERS.toString(), dtoForSession);
        request.getRequestDispatcher("/MainPageServlet").forward(request, response);
    }
}
