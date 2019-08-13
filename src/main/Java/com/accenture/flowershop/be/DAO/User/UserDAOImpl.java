package com.accenture.flowershop.be.DAO.User;

import com.accenture.flowershop.be.Entity.User.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {


    @PersistenceContext
    private EntityManager em;

    private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);

    @Transactional
    @Override
    public UserEntity findUserByLogin(String login) {
        try {
            TypedQuery<UserEntity> query =
                    em.createQuery("select e from UserEntity e where e.login =:LOGIN" , UserEntity.class);
            query.setParameter("LOGIN", login);
            return query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    @Override
    public List<UserEntity> getUserList() {
        /*try {
            return em.createQuery("select e from User e", User.class).getResultList();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return null;
    */
        return em.createQuery("SELECT e from UserEntity e", UserEntity.class).getResultList();
    }

    @Transactional
    @Override
    public void addUser(UserEntity user) {
        em.persist(user);
    }

    @Transactional
    @Override
    public void deleteUser(UserEntity user) {
        em.remove(user);
    }

    @Transactional
    @Override
    public void updateUser(UserEntity user) {
        em.refresh(user);
    }
}
