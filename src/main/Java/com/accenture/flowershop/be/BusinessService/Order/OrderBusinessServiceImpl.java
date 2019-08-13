package com.accenture.flowershop.be.BusinessService.Order;

import com.accenture.flowershop.be.DAO.Flower.FlowerDAO;
import com.accenture.flowershop.be.DAO.Order.OrderDAO;
import com.accenture.flowershop.be.Entity.Flower.FlowerEntity;
import com.accenture.flowershop.be.Entity.Order.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private FlowerDAO flowerDAO;

    @Override
    public void saveOrder(OrderEntity order) {
        orderDAO.saveOrder(order);
    }

    @Override
    public void delete(OrderEntity order) {
        orderDAO.delete(order);
    }

    @Override
    public void updateOrder(OrderEntity order) {
        orderDAO.updateOrder(order);
    }

    @Override
    public OrderEntity findOrder(long orderId) {
        return orderDAO.findOrder(orderId);
    }

    @Override
    public List<OrderEntity> findAll() {
        return orderDAO.findAllOrders() ;
    }

    @Override
    public OrderEntity getInfoAboutOrder(OrderEntity order) {
        return orderDAO.getInfoAboutOrder(order);
    }

    //тут надо подумать
    @Override
    public double priceOfOne(FlowerEntity flower, int amount) {
        if (checkFlowerAmount(flower, amount)) {
            return flowerDAO.findFlowerByName(flower.getFlowerName()).getPrice() * amount;
        }else
            return 0;
    }

    @Override
    public double getTotalPrice() {
        return 0;
    }

    @Override
    public boolean checkFlowerAmount(FlowerEntity flower, int amount) {

        if (flowerDAO.findFlowerByName(flower.getFlowerName()).getNumber() >= amount) {

            return true;
        } else {
            return false;
        }
    }
}