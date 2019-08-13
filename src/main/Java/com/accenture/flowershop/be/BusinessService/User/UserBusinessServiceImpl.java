package com.accenture.flowershop.be.BusinessService.User;

import com.accenture.flowershop.be.DAO.User.UserDAO;
import com.accenture.flowershop.be.Entity.User.UserEntity;
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
        if (userDAO.getUserList() != null) {
            try {
                if (userDAO.findUserByLogin(login).getLogin().equals(login)) {
                    log.debug("Verification successful");
                    return true;
                } else {
                    log.debug("Wrong login or password");
                    return false;
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            return false;
        }
        return false;
    }
    //доделать!!!
    @Override
    public boolean userRegistration(UserEntity user) {
        if (!userDAO.getUserList().isEmpty()) {
            if (userDAO.findUserByLogin(user.getLogin()) != null && user.getPassword() != null) {
                //if (!StringUtils.isWhitespaceOrEmpty(user.getLogin()) && !StringUtils.isWhitespaceOrEmpty(user.getLogin())) {
                try {
                    if (userDAO.findUserByLogin(user.getLogin()) == null) {
                        userDAO.addUser(user);
                        log.debug("Registration successful");
                        return true;
                    }
                } catch (NoResultException e) {
                    e.printStackTrace();
                    return false;
                }
                return false;
            }
            return false;
        } else {
            userDAO.addUser(user);
            return true;
        }
    }

    @Override
    public void deleteUser(UserEntity user) {
        userDAO.deleteUser(user);
    }

    @Override
    public void updateUser(UserEntity user) {
        userDAO.updateUser(user);
    }

    @Override
    public UserEntity findUserByLogin(String login) {
        return userDAO.findUserByLogin(login);
    }
}
