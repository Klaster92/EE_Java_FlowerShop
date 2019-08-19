package com.accenture.flowershop.be.BusinessService.User;


import com.accenture.flowershop.be.BusinessService.Utils.ServiceException;
import com.accenture.flowershop.be.Entity.User.User;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface UserBusinessService {

    User userVerification(String login, String password);

    User userRegistration(User user);

    User updateBalance(String login, BigDecimal balance);

    void deleteUser(User user);

    void updateUser(User user);

    User findUserByLogin(String login);

    User getUserById(Long id) throws ServiceException;

    @Transactional(rollbackFor = ServiceException.class)
    void updateDiscount(Long idUser, Integer newDiscount) throws ServiceException;
}