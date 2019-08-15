package com.accenture.flowershop.be.BusinessService.Order;

import com.accenture.flowershop.be.Entity.Order.Order;
import com.accenture.flowershop.be.Entity.User.User;

import java.util.List;

public interface OrderBusinessService  {

    Order saveOrder(Order order);

    List<Order> getAllOrders();

    void completeOrder(Long id);

    void delete(Order order);

    List<Order> getOrdersByUser(User user);





}