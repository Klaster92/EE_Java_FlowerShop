package com.accenture.flowershop.be.BusinessService.Order;

import com.accenture.flowershop.be.Entity.Flower.FlowerEntity;
import com.accenture.flowershop.be.Entity.Order.OrderEntity;
import java.util.List;

public interface OrderBusinessService  {

    void saveOrder(OrderEntity order);

    void delete(OrderEntity order);

    void  updateOrder(OrderEntity order);

    OrderEntity findOrder(long orderId);

    List<OrderEntity> findAll();

    OrderEntity getInfoAboutOrder(OrderEntity order);

    double priceOfOne(FlowerEntity flower, int amount);

    double getTotalPrice();

    boolean checkFlowerAmount(FlowerEntity flower, int amount);
}