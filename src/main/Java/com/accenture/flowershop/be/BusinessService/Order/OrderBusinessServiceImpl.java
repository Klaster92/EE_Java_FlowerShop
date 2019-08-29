package com.accenture.flowershop.be.BusinessService.Order;

import com.accenture.flowershop.be.BusinessService.User.UserBusinessService;
import com.accenture.flowershop.be.BusinessService.Utils.ServiceException;
import com.accenture.flowershop.be.DAO.Flower.FlowerDAO;
import com.accenture.flowershop.be.DAO.Order.OrderDAO;
import com.accenture.flowershop.be.Entity.Flower.Flower;
import com.accenture.flowershop.be.Entity.Order.Order;
import com.accenture.flowershop.be.Entity.Order.OrderPos;
import com.accenture.flowershop.be.Entity.User.User;
import com.accenture.flowershop.fe.dto.OrderPosDto;
import com.accenture.flowershop.fe.enums.OrderStatus;
import com.accenture.flowershop.fe.enums.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {

    private static final Logger log = 	LoggerFactory.getLogger(OrderBusinessServiceImpl.class);

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private UserBusinessService userBusinessService;

    @Autowired
    private FlowerDAO flowerDAO;

    @Override
    public Order saveOrder(Order order) {
        log.debug("saveOrder");
        orderDAO.saveOrder(order);
        return order;
    }

    @Override
    public void delete(Order order) {
        log.debug("Order delete");
        orderDAO.delete(order);
    }

    @Override
    public List<Order> getOrdersByUser(User user) {
        return null;
    } // дописать, не факт, что понадобится

    @Override
    public List<Order> getListOrders() {
        return orderDAO.findAllOrders();
    }

    @Override
    public List<Order> getAllOrders(User user) {
        log.debug("getAllOrders");
        if(user.getRole() == UserType.USER) {
            return orderDAO.getAllMyOrders(user.getId());
        }
        return orderDAO.findAllOrders() ;
    }

    @Override
    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }

    @Override
    public void completeOrder(Long id) {
        log.debug("completeOrder");
        Order order = orderDAO.findOrder(id);
        order.setStatus(OrderStatus.CLOSED);
        orderDAO.updateOrder(order);
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void addOrder(Order order) throws ServiceException {
        log.debug("addOrder");
        order.setStatus(OrderStatus.CREATED);
        if (order.getOrderPos().isEmpty()) {
            throw new ServiceException(ServiceException.ERROR_BASKET);
        }
        try {
            orderDAO.saveOrder(order);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.ERROR_INSERT);
        }

    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void payOrder(Long idOrder, Long idUser) throws ServiceException {
        log.debug("payOrder");
            Order order = orderDAO.findOrder(idOrder);
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
            orderDAO.updateOrder(order);
            for (OrderPos orderPosition : order.getOrderPos()) {
                Flower flower = orderPosition.getFlower();
                if (flower.getNumber() < orderPosition.getNumber()) {
                    throw new ServiceException(ServiceException.ERROR_FLOWERSTOCK);
                }
                flower.setNumber(flower.getNumber() - orderPosition.getNumber());
                flowerDAO.updateFlower(flower);
            }
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void closeOrder(Long idOrder) throws ServiceException {
        log.debug("closeOrder");
        try{
            Order order = orderDAO.findOrder(idOrder);
            order.close();
        }catch (NoResultException e) {
            throw new ServiceException(ServiceException.ERROR_FIND_ORDER);
        }
    }

    public List<Order> getAllMyOrders(Long userId) {
        return getAllMyOrders(userId);
    }

    @Override
    public void computePrice(OrderPosDto order){
        log.debug("computeOrder");
        BigDecimal price = order.getFlower().getPrice().multiply(new BigDecimal(order.getNumber()));
        order.setPrice(price.setScale(2, RoundingMode.HALF_DOWN));
    }
}

