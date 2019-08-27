package com.accenture.flowershop.be.DAO.Flower;

import com.accenture.flowershop.be.BusinessService.Utils.FlowerFilter;
import com.accenture.flowershop.be.Entity.Flower.Flower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Transactional
@Repository
public class FlowerDAOImpl implements FlowerDAO {

    @PersistenceContext
    private EntityManager em;

    private static final Logger log = LoggerFactory.getLogger(FlowerDAOImpl.class);

    @Override
    public Flower findFlowerById(Long id) {
        try{
            log.debug("finding flower by name");
            TypedQuery<Flower> query;
            query = em.createQuery("select e from Flower e where e.id=:id", Flower.class);
            query.setParameter("id", id).getSingleResult();
            return query.getSingleResult();
        } catch (NoResultException e) {
         return null;
        }
    }

    public Flower findFlowerByName(String name) {
        try{
            log.debug("finding flower by name");
            TypedQuery<Flower> query;
            query = em.createQuery("select e from Flower e where e.flower_name=:name", Flower.class);
            query.setParameter("name", name).getSingleResult();
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Flower> getFlowerList() {
        log.debug("getting flowers list");
        TypedQuery<Flower> query;
        query = em.createQuery("select e from Flower e", Flower.class);
        return query.getResultList();
    }

    @Override
    public Flower addFlower(Flower flower) {
            log.debug("add flower");
            em.persist(flower);
            em.flush();
            return  flower;


    }

    @Override
    public void deleteFlower(Flower flower) {
        log.debug("delete flower");
        em.remove(flower);
        em.flush();
    }

    @Override
    public void updateFlower(Flower flower) {
        log.debug("updating flower");
        em.merge(flower);
        em.flush();
    }

    @Override
    public BigDecimal getMinPrice() {
            Query qry = em.createQuery("SELECT MIN (t.price) FROM Flower t");
            Object obj = qry.getSingleResult();
            if (obj == null)
                return BigDecimal.valueOf(0);
            return (BigDecimal) obj;
    }
    @Override
    public BigDecimal getMaxPrice() {
            Query qry = em.createQuery("SELECT MAX(t.price) FROM Flower t");
            Object obj = qry.getSingleResult();
            if (obj == null)
                return BigDecimal.valueOf(0);
            return (BigDecimal) obj;
    }

    @Override
    public List<Flower> searchFilter(FlowerFilter filter) {
        TypedQuery<Flower> q = em.createQuery("select f from Flower f where f.price >= minPrice and " +
                "f.price <= maxPrice and f.NAME_FLOWER =: name ", Flower.class);
        q.setParameter("minPrice", filter.getMinPrice());
        q.setParameter("maxPrice", filter.getMaxPrice());
        q.setParameter("name", filter.getName());
        return q.getResultList();
    }


}
