package com.accenture.flowershop.be.DAO.User;

import com.accenture.flowershop.be.Entity.User.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    User findUserByLogin(String login);

    List<User> getUserList();

    Optional<User> getUserById(Long id);

    User addUser(User user);

    void deleteUser(User user);

    User updateUser(User user);
}
