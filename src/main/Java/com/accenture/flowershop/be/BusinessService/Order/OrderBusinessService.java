package com.accenture.flowershop.be.BusinessService.Order;

import com.accenture.flowershop.be.Entity.Flower.Flower;
import com.accenture.flowershop.be.Entity.Order.Order;
import java.util.List;

public interface OrderBusinessService  {

    void saveOrder(Order order);

    void delete(Order order);

    void  updateOrder(Order order);

    Order findOrder(long orderId);

    List<Order> findAll();

    double getTotalPrice();

}