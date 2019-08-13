package com.accenture.flowershop.be.DAO.Order;

import com.accenture.flowershop.be.Entity.Order.OrderEntity;

import java.util.List;

public interface OrderDAO {

    void saveOrder(OrderEntity order);

    void delete(OrderEntity order);

    void updateOrder(OrderEntity order);

    OrderEntity findOrder(long orderId);

    List<OrderEntity> findAllOrders();

    OrderEntity getInfoAboutOrder(OrderEntity order);

}