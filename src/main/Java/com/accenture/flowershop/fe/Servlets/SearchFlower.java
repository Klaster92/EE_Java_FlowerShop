package com.accenture.flowershop.fe.Servlets;

import com.accenture.flowershop.be.BusinessService.Flower.FlowerBusinessService;
import com.accenture.flowershop.be.BusinessService.Utils.FlowerFilter;
import com.accenture.flowershop.be.BusinessService.Utils.Mapper;
import com.accenture.flowershop.fe.dto.FlowerDto;
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
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
@WebServlet
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

        BigDecimal from = BigDecimal.valueOf(Long.parseLong(req.getParameter("from")));
        BigDecimal to = BigDecimal.valueOf(Long.parseLong(req.getParameter("to")));
        String name = req.getParameter("name");

        FlowerFilter filter = new FlowerFilter(from, to, name);
        List<FlowerDto> flowerDtos = Mapper.mapFlowers(flowerBusinessService.searchFilter(filter));
        req.setAttribute(SessionAttribute.FILTER.toString(), filter);
        req.setAttribute(SessionAttribute.FLOWERS.toString(), flowerDtos);
        req.getRequestDispatcher("/MainPageServlet").forward(req, resp);
    }
}
