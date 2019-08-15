package com.accenture.flowershop.be.DAO.Order;

import com.accenture.flowershop.be.Entity.Order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
@Repository
public class OrderDAOImpl implements OrderDAO {

    @PersistenceContext
    private EntityManager em;

    private static final Logger log = LoggerFactory.getLogger(OrderDAOImpl.class);

    @Override
    public Order saveOrder(Order order) {
        try {
            log.debug("save order");
            em.persist(order);
            em.flush();
            return order;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void delete(Order order) {
        log.debug("delete order");
        em.remove(order);
        em.flush();
    }

    @Override
    public Order updateOrder(Order order) {
        try {
            log.debug("update order");
            em.refresh(order);
            em.flush();
            return order;
        }catch (NoResultException e) {
         return null;
        }
    }

    @Override
    public Order findOrder(long orderId) {
        try{
            TypedQuery<Order> query;
            query = em.createQuery("select e from Order e where e.orderId=:ID", Order.class);
            return query.setParameter("ID",orderId).getSingleResult();
        } catch (NoResultException e) {
            log.debug("Not find order");
            return null;
        }
    }

    @Override
    public List<Order> findAllOrders() {
        try {
            TypedQuery<Order> query;
            query = em.createQuery("select e from Order e", Order.class);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}

