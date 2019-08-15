package com.accenture.flowershop.be.DAO.Order;

import com.accenture.flowershop.be.Entity.Order.Order;

import java.util.List;

public interface OrderDAO {

    Order saveOrder(Order order);

    void delete(Order order);

    Order updateOrder(Order order);

    Order findOrder(long orderId);

    List<Order> findAllOrders();

}