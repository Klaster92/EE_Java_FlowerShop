package com.accenture.flowershop.be.DAO.Flower;

import com.accenture.flowershop.be.Entity.Flower.FlowerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Service
public class FlowerDAOImpl implements FlowerDAO {

    @PersistenceContext
    private EntityManager em;

    private static final Logger log = LoggerFactory.getLogger(FlowerDAOImpl.class);

    @Override
    public FlowerEntity findFlowerByName(String flower_name) {
        log.debug("finding flower by name");
        return (FlowerEntity) em.createQuery("select e from Flower e where e.flower_name=:flower_name");
    }

    @Override
    public List<FlowerEntity> getFlowerList() {
        log.debug("getting flowers list");
        return em.createQuery("select e from Flower e", FlowerEntity.class).getResultList();
    }

    @Transactional
    @Override
    public void addFlower(FlowerEntity flower) {
        log.debug("saving flower");
        em.persist(flower);
    }

    @Transactional
    @Override
    public void deleteFlower(FlowerEntity flower) {
        log.debug("deleting flower");
        em.remove(flower);
    }

    @Transactional
    @Override
    public void updateFlower(FlowerEntity flower) {
        log.debug("updating flower");
        em.refresh(flower);
    }
}
