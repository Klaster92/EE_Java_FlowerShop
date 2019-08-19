package com.accenture.flowershop.be.DAO.repositories;

import com.accenture.flowershop.be.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByLogin(String login);
}
