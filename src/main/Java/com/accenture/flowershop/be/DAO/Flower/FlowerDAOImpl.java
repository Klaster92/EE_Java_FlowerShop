package com.accenture.flowershop.be.DAO.Flower;

import com.accenture.flowershop.be.Entity.Flower.Flower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
@Repository
public class FlowerDAOImpl implements FlowerDAO {

    @PersistenceContext
    private EntityManager em;

    private static final Logger log = LoggerFactory.getLogger(FlowerDAOImpl.class);

    @Override
    public Flower findFlowerByName(String flower_name) {
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
    public void addFlower(Flower flower) {
        log.debug("saving flower");
        em.persist(flower);
        em.flush();
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
        em.refresh(flower);
        em.flush();
    }
}
