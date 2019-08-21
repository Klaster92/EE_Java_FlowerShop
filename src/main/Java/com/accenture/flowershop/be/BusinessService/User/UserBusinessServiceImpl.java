package com.accenture.flowershop.be.BusinessService.User;

import com.accenture.flowershop.be.BusinessService.Utils.ServiceException;
import com.accenture.flowershop.be.DAO.User.UserDAO;
import com.accenture.flowershop.be.Entity.User.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        User user = findUserByLogin(login);
        if (user.getPassword().equals(password)) {
            log.debug("Login Access");
            return user;
        }
        return null;
    }

    @Override
    public User userRegistration(User user) {
        if(StringUtils.containsWhitespace(user.getLogin()) && StringUtils.containsWhitespace(user.getPassword())) {
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
    public Boolean checkLogin(String login) {
        log.debug("Check login");
            User user = userDAO.findUserByLogin(login);
            if(user != null) {
                return true;
            }
        return false;
    }

    @Override
    public User updateBalance(String login, BigDecimal balance) {
        User user = userDAO.findUserByLogin(login);
        BigDecimal oldBalance = user.getBalance();
        user.setBalance((oldBalance.subtract(balance)));
        userDAO.updateUser(user);
        log.debug("Update balance");
        return user;
    }

    @Override
    public void deleteUser(User user) {
        log.debug("Delete User");
        userDAO.deleteUser(user);
    }

    @Override
    public void updateUser(User user) {
        log.debug("Update user");
        userDAO.updateUser(user);
    }

    @Override
    public User findUserByLogin(String login) {
        log.debug("FindUserByLogin");
        return userDAO.findUserByLogin(login);
    }

    @Override
    public User getUserById(Long id) throws ServiceException {
        log.debug("GetUserByID");
        User user = userDAO.getUserById(id);
        if(user != null) {
            return user;
        }
        throw new ServiceException(ServiceException.ERROR_FIND_USER);
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void updateDiscount(Long idUser, Integer newDiscount) throws ServiceException {
        log.debug("UpdateDiscount");
        if(newDiscount < 0){
            throw new ServiceException(ServiceException.ERROR_INVALIDATE_DATA);
        }
        User user = getUserById(idUser);
        user.setDiscount(newDiscount);
        userDAO.updateUser(user);
    }

    @Override
    public void pay(Long idUser, BigDecimal priceOrder) throws ServiceException {
        log.debug("pay");
        BigDecimal balance;
        User user = getUserById(idUser);
        if ((balance = user.getBalance().subtract(priceOrder)).compareTo(BigDecimal.ZERO) != -1) {
            user.setBalance(balance);
            userDAO.updateUser(user);
        } else {
            throw new ServiceException(ServiceException.ERROR_USER_BALANCE);
        }
    }
}
