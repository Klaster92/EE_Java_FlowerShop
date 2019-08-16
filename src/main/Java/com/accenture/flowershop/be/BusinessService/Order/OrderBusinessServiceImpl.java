package com.accenture.flowershop.be.BusinessService.Order;

import com.accenture.flowershop.be.DAO.Order.OrderDAO;
import com.accenture.flowershop.be.Entity.Order.Order;
import com.accenture.flowershop.be.Entity.User.User;
import com.accenture.flowershop.fe.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public Order saveOrder(Order order) {
            orderDAO.saveOrder(order);
            return order;
    }

    @Override
    public void delete(Order order) {
        orderDAO.delete(order);
    }

    @Override
    public List<Order> getOrdersByUser(User user) {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDAO.findAllOrders() ;
    }

    @Override
    public void completeOrder(Long id) {
        Order order = orderDAO.findOrder(id);
        order.setStatus(OrderStatus.COMPLETED);
        order.setCompleteDate(new Date());
        orderDAO.updateOrder(order);
    }
}