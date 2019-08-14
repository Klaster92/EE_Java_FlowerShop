package com.accenture.flowershop.be.BusinessService.User;


import com.accenture.flowershop.be.Entity.User.User;

public interface UserBusinessService {

    boolean userVerification(String login, String password);

    boolean userRegistration(User user);

    void deleteUser(User user);

    void updateUser(User user);

    User findUserByLogin(String login);
}