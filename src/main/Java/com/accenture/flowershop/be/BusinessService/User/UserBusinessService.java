package com.accenture.flowershop.be.BusinessService.User;

import com.accenture.flowershop.be.Entity.User.UserEntity;

public interface UserBusinessService {

    boolean userVerification(String login, String password);

    boolean userRegistration(UserEntity user);

    void deleteUser(UserEntity user);

    void updateUser(UserEntity user);

    UserEntity findUserByLogin(String login);
}