package com.accenture.flowershop.be.DAO.Flower;

import com.accenture.flowershop.be.BusinessService.Utils.FlowerFilter;
import com.accenture.flowershop.be.Entity.Flower.Flower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
            query = em.createQuery("select e from Flower e where e.flower_name=:flower_name", Flower.class);
            return query.getSingleResult();
        } catch (NoResultException e) {
         return null;
        }
    }

    @Override
    public List<Flower> getFlowerList() {
        try {
            log.debug("getting flowers list");
            TypedQuery<Flower> query;
            query = em.createQuery("select e from Flower e", Flower.class);
            return query.getResultList();
        } catch (NoResultException e) {
         return null;
        }
    }

    @Override
    public Flower addFlower(Flower flower) {
            log.debug("saving flower");
            em.persist(flower);
            em.flush();
            return  flower;


    }

    @Override
    public void deleteFlower(Flower flower) {
        log.debug("deleting flower");
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
    public List<Flower> searchFilter (FlowerFilter filter) {
        String temp = filter.toString();
        TypedQuery<Flower> q = em.createQuery("select f from Flower f where " + temp, Flower.class);
        q.setParameter("minprice", new BigDecimal(filter.getMinPrice()));
        q.setParameter("maxprice", new BigDecimal(filter.getMaxPrice()));
        q.setParameter("name", filter.getName());
        return q.getResultList();

    }
}
