package com.accenture.flowershop.be.BusinessService.Order;

import com.accenture.flowershop.be.BusinessService.User.UserBusinessService;
import com.accenture.flowershop.be.BusinessService.Utils.ServiceException;
import com.accenture.flowershop.be.DAO.Order.OrderDAO;
import com.accenture.flowershop.be.DAO.repositories.OrderRepository;
import com.accenture.flowershop.be.Entity.Flower.Flower;
import com.accenture.flowershop.be.Entity.Order.Order;
import com.accenture.flowershop.be.Entity.Order.OrderPos;
import com.accenture.flowershop.be.Entity.User.User;
import com.accenture.flowershop.fe.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private UserBusinessService userBusinessService;

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
        orderDAO.updateOrder(order);
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void addOrder(Order order) throws ServiceException {
        order.setStatus(OrderStatus.CREATED);
        if (order.getOrderPos().isEmpty()) {
            throw new ServiceException(ServiceException.ERROR_BASKET);
        }
        try {
            orderRepository.saveAndFlush(order);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.ERROR_INSERT);
        }

    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void payOrder(Long idOrder, Long idUser) throws ServiceException {
        Order order = orderRepository.findById(idOrder)
                .orElseThrow(() -> new ServiceException(ServiceException.ERROR_FIND_ORDER));
        if (idUser == null) {
            throw new ServiceException(ServiceException.ERROR_INVALIDATE_DATA);
        }
        if (!order.getUser().getId().equals(idUser)) {
            throw new RuntimeException("Order not found for user");
        }

        if (order.getStatus() != OrderStatus.CREATED) {
            throw new ServiceException(ServiceException.ERROR_FIND_ORDER);
        }

        userBusinessService.pay(idUser, order.getTotalPrice());//**вычет стоимости покупки
        order.setStatus(OrderStatus.PAID);
        for (OrderPos orderPosition : order.getOrderPos()) {   //**изменение кол-ва цветов на складе
            Flower flower = orderPosition.getFlower();
            if (flower.getNumber() < orderPosition.getNumber()) {
                throw new ServiceException(ServiceException.ERROR_FLOWERSTOCK);
            }
            flower.setNumber(flower.getNumber() - orderPosition.getNumber());
        }
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void closeOrder(Long idOrder) throws ServiceException {
        Order order = orderRepository.findById(idOrder).
                orElseThrow(() -> new ServiceException(ServiceException.ERROR_FIND_ORDER));
        order.close();
    }
}