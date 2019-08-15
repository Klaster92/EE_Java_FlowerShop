package com.accenture.flowershop.be.BusinessService.User;


import com.accenture.flowershop.be.Entity.User.User;

public interface UserBusinessService {

    User userVerification(String login, String password);

    User userRegistration(User user);

    User updateBalance(String login, double balance);

    User getInfo(String login);

    void deleteUser(User user);

    void updateUser(User user);

    User findUserByLogin(String login);
}