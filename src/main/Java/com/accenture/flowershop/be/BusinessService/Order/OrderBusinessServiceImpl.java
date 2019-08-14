package com.accenture.flowershop.be.BusinessService.Order;

import com.accenture.flowershop.be.DAO.Flower.FlowerDAO;
import com.accenture.flowershop.be.DAO.Order.OrderDAO;
import com.accenture.flowershop.be.Entity.Flower.Flower;
import com.accenture.flowershop.be.Entity.Order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private FlowerDAO flowerDAO;

    @Override
    public void saveOrder(Order order) {
        orderDAO.saveOrder(order);
    }

    @Override
    public void delete(Order order) {
        orderDAO.delete(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }

    @Override
    public Order findOrder(long orderId) {
        return orderDAO.findOrder(orderId);
    }

    @Override
    public List<Order> findAll() {
        return orderDAO.findAllOrders() ;
    }

    @Override
    public double getTotalPrice() {
        return 0;
    }
}