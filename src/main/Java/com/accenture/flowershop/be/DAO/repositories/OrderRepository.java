package com.accenture.flowershop.be.DAO.repositories;

import com.accenture.flowershop.be.Entity.Order.Order;
import com.accenture.flowershop.be.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> getOrdersByUser(User user);
}
