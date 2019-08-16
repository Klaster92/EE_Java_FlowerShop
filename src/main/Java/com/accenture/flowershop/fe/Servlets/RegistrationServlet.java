package com.accenture.flowershop.fe.Servlets;

import com.accenture.flowershop.be.BusinessService.User.UserBusinessService;
import com.accenture.flowershop.be.Entity.User.User;
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
@WebServlet(urlPatterns = "/Registration")
public class RegistrationServlet extends HttpServlet {

    @Autowired
    private UserBusinessService userBusinessService;

    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

    public void init(ServletConfig config) throws ServletException {
        super.init((ServletConfig) config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NullPointerException {

        User user = new User();
        user.setLogin(request.getParameter("login"));
        user.setName(request.getParameter("FirstName"));
        user.setLastName(request.getParameter("LastName"));
        user.setMiddleName(request.getParameter("MiddleName"));
        user.setPassword(request.getParameter("Password"));
        user.setAddress(request.getParameter("Address"));
        user.setEmail(request.getParameter("Email"));
        user.setPhoneNumber(request.getParameter("PhoneNumber"));
        if (userBusinessService.userRegistration(user) != null) {
            request.getRequestDispatcher("/WEB-INF/lib/LoginPage.jsp").forward(request, response);
        }
        request.getRequestDispatcher("/WEB-INF/lib/WrongData.jsp").forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/lib/RegistrationPage.jsp").forward(request, response);
    }
}