package com.accenture.flowershop.fe.Servlets;

import com.accenture.flowershop.be.BusinessService.Flower.FlowerBusinessService;
import com.accenture.flowershop.be.BusinessService.Utils.FlowerFilter;
import com.accenture.flowershop.fe.enums.SessionAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/service/search")
public class SearchFlower extends HttpServlet {

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

        String from = req.getParameter("from");
        String to = req.getParameter("to");
        String name = req.getParameter("name");
        FlowerFilter filter = new FlowerFilter(from, to, name);
        req.setAttribute(SessionAttribute.FILTER.toString(), filter);
        req.getRequestDispatcher("/service/mainpage").forward(req, resp);
    }
}
