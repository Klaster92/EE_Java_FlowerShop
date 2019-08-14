package com.accenture.flowershop.be.BusinessService.User;

import com.accenture.flowershop.be.DAO.User.UserDAO;
import com.accenture.flowershop.be.Entity.User.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {

    @Autowired
    private UserDAO userDAO;

    private static final Logger log = 	LoggerFactory.getLogger(UserBusinessServiceImpl.class);

    @Override
    public boolean userVerification(String login, String password) throws NullPointerException {
        if(login.isEmpty() || password.isEmpty()) {
            log.debug("Wrong login or password");
            return false;
        }
        try {
            if (findUserByLogin(login).getPassword().equals(password)) {
                return true;
            }
        }catch (NullPointerException e) {
            log.debug("Wrong login or password");
            return false;
        }
        log.debug("Wrong login or password");
        return false;
    }

    @Override
    public boolean userRegistration(User user) {
        if (userDAO.findUserByLogin(user.getLogin()) == null) {
            userDAO.addUser(user);
            log.debug("Registration successful");
            return true;
        }
        log.debug("Registration invalid");
        return false;
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
