package com.accenture.flowershop.be.DAO.Order;

import com.accenture.flowershop.be.Entity.Order.OrderEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class OrderDAOImpl implements OrderDAO {

    @PersistenceContext
    private EntityManager em;

    private static final Logger log = LoggerFactory.getLogger(OrderDAOImpl.class);

    @Transactional
    @Override
    public void saveOrder(OrderEntity order) {
        log.debug("save order");
        em.persist(order);
    }

    @Transactional
    @Override
    public void delete(OrderEntity order) {
        log.debug("delete order");
        em.remove(order);
    }

    @Transactional
    @Override
    public void updateOrder(OrderEntity order) {
        log.debug("update order");
        em.refresh(order);
    }

    @Override
    public OrderEntity findOrder(long orderId) {
        log.debug("find order");
        return em.createQuery("select e from OrderEntity e where e.orderId=:ID", OrderEntity.class).setParameter("ID",orderId).getSingleResult();
    }

    @Override
    public List<OrderEntity> findAllOrders() {
        log.debug("get orders list");
        return em.createQuery("select e from OrderEntity e", OrderEntity.class).getResultList();
    }

    @Override
    public OrderEntity getInfoAboutOrder(OrderEntity order) {
        log.debug("info about order");
        return em.createQuery("select e from OrderEntity e", OrderEntity.class).getSingleResult();
    }
}

