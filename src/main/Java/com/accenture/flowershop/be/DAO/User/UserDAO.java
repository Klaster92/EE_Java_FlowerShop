package com.accenture.flowershop.be.DAO.User;

import com.accenture.flowershop.be.Entity.User.User;

import java.util.List;

public interface UserDAO {

    User findUserByLogin(String login);

    List<User> getUserList();

    void addUser(User user);

    void deleteUser(User user);

    void updateUser(User user);
}
