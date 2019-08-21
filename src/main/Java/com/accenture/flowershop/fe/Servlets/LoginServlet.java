package com.accenture.flowershop.fe.Servlets;

import com.accenture.flowershop.be.BusinessService.User.UserBusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Service
@WebServlet
public class LoginServlet extends HttpServlet {

    @Autowired
    private UserBusinessService userBusinessService;

    private static final Logger log = 	LoggerFactory.getLogger(LoginServlet.class);

    private static final long serialVersionUID = 1L;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/lib/LoginPage.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("login") != null) {

            String login = request.getParameter("login");
            String password = request.getParameter("password");
            try {

                if (userBusinessService.userVerification(login, password) != null) {

                    request.getRequestDispatcher("/MainPageServlet").forward(request, response);

                } else {
                    request.getRequestDispatcher("/LoginServlet");
                }
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        if (request.getParameter("To registration") != null) {
            request.getRequestDispatcher("/RegistrationServlet").forward(request, response);
        }
    }
}
