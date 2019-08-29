package com.accenture.flowershop.be.BusinessService.Order;

import com.accenture.flowershop.be.BusinessService.Utils.ServiceException;
import com.accenture.flowershop.be.Entity.Order.Order;
import com.accenture.flowershop.be.Entity.User.User;
import com.accenture.flowershop.fe.dto.OrderPosDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderBusinessService  {

    Order saveOrder(Order order);

    List<Order> getListOrders();

    List<Order> getAllOrders(User user);

    void updateOrder(Order order);

    void completeOrder(Long id);

    void delete(Order order);

    List<Order> getOrdersByUser(User user);


    @Transactional(rollbackFor = ServiceException.class)
    void addOrder(Order order) throws ServiceException;

    @Transactional(rollbackFor = ServiceException.class)
    void payOrder(Long idOrder, Long idUser) throws ServiceException;

    @Transactional(rollbackFor = ServiceException.class)
    void closeOrder(Long idOrder) throws ServiceException;

    void computePrice(OrderPosDto order);
}