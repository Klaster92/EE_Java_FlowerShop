package com.accenture.flowershop.fe.Servlets;

import com.accenture.flowershop.fe.dto.UserDto;
import com.accenture.flowershop.fe.enums.SessionAttribute;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service
@WebFilter
public class FlowerShopFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession(false);
        if(session != null) {
            UserDto user = (UserDto) session.getAttribute(SessionAttribute.USER.toString());
            if (user != null) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
            else throw new ServletException("Bad choice");
        }
        else throw new ServletException("Bad choice");
    }

    @Override
    public void destroy(){

    }
}
