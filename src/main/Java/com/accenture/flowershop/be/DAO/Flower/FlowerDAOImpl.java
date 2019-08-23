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
    public List<Flower> searchFilter (FlowerFilter filter) {
        log.debug("searchFilter");
        TypedQuery<Flower> q = em.createQuery("select f from Flower f where f.price between :minprice and :maxprice and f.nameFlower like CONCAT('%',:name,'%')", Flower.class);
        q.setParameter("name", filter.getName());
        return q.getResultList();

    }
}
