package com.accenture.flowershop.be.DAO.User;

import com.accenture.flowershop.be.Entity.User.UserEntity;

import java.util.List;

public interface UserDAO {

    UserEntity findUserByLogin(String login);

    List<UserEntity> getUserList();

    void addUser(UserEntity user);

    void deleteUser(UserEntity user);

    void updateUser(UserEntity user);
}
