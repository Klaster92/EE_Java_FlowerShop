package com.accenture.flowershop.fe.Servlets;

import com.accenture.flowershop.be.BusinessService.Order.OrderBusinessService;
import com.accenture.flowershop.be.BusinessService.User.UserBusinessService;
import com.accenture.flowershop.be.BusinessService.Utils.MapperService;
import com.accenture.flowershop.be.BusinessService.Utils.ServiceException;
import com.accenture.flowershop.be.Entity.Order.Order;
import com.accenture.flowershop.fe.dto.OrderDto;
import com.accenture.flowershop.fe.enums.SessionAttribute;
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

@WebServlet(urlPatterns = "/service/createOrder")
public class CreateOrder extends HttpServlet {

    @Autowired
    MapperService mapper;
    @Autowired
    private OrderBusinessService orderBusinessService;
    @Autowired
    private UserBusinessService userBusinessService;

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
        try {
            orderBusinessService.addOrder(mapper.map(orderDto, Order.class));
            session.removeAttribute(SessionAttribute.BASKET.toString());
            req.setAttribute("order_msg", "Order is created");
        }
        catch(ServiceException e) {
            req.setAttribute("bskt_err", e.getMessage());
        }
        finally{
            req.getRequestDispatcher("/service/mainpage").forward(req, resp);
        }
    }
}
