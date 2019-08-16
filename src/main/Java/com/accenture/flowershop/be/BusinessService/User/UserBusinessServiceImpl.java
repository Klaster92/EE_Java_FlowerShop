package com.accenture.flowershop.be.BusinessService.User;

import com.accenture.flowershop.be.DAO.User.UserDAO;
import com.accenture.flowershop.be.Entity.User.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {

    @Autowired
    private UserDAO userDAO;

    private static final Logger log = 	LoggerFactory.getLogger(UserBusinessServiceImpl.class);

    @Override
    public User userVerification(String login, String password) throws NullPointerException {
        if(login.isEmpty() || password.isEmpty()) {
            log.debug("Wrong login or password");
            return null;
        }
        try {
            if (findUserByLogin(login) != null ) {
                if(findUserByLogin(login).getPassword().equals(password))
                return userDAO.findUserByLogin(login);
            }
        }catch (NullPointerException e) {
            log.debug("Wrong login or password");
            return null;
        }
        log.debug("Wrong login or password");
        return null;
    }

    @Override
    public User userRegistration(User user) {
        if(StringUtils.containsWhitespace(user.getLogin()) && !StringUtils.containsWhitespace(user.getPassword())) {
            log.debug("Registration invalid");
            return null;
        }
        if (userDAO.findUserByLogin(user.getLogin()) == null) {
            userDAO.addUser(user);
            log.debug("Registration successful");
            return user;
        }
        log.debug("Registration invalid");
        return null;
    }

    @Override
    public User updateBalance(String login, BigDecimal balance) {
        User user = userDAO.findUserByLogin(login);
        BigDecimal oldBalance = user.getBalance();
        user.setBalance((oldBalance.subtract(balance)));
        userDAO.updateUser(user);
        return user;
    }

    @Override
    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public User findUserByLogin(String login) {
        return userDAO.findUserByLogin(login);
    }
}
