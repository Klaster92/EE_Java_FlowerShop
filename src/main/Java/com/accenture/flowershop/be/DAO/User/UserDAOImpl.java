package com.accenture.flowershop.be.DAO.User;

import com.accenture.flowershop.be.Entity.User.User;
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
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);

    @Override
    public User findUserByLogin(String login) {
        log.debug("findUserByLogin");
        try {
            TypedQuery<User> query =
                    em.createQuery("select e from User e where e.login =:LOGIN" , User.class);
            query.setParameter("LOGIN", login);
            return query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> getUserList() {
        log.debug("getUserList");
        TypedQuery<User> query;
        query = em.createQuery("SELECT e from User e", User.class);
        return query.getResultList();
    }

    @Override
    public User getUserById(Long id) {
        log.debug("getUserById");
        try{
            TypedQuery<User> query;
            query = em.createQuery("select e from User where e.id_user =:id", User.class);
            return query.getSingleResult();
        }catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User addUser(User user) {
        log.debug("addUser");
        em.persist(user);
        em.flush();
        return user;
    }

    @Override
    public void deleteUser(User user) {
        log.debug("deleteUser");
        em.remove(user);
        em.flush();
    }

    @Override
    public User updateUser(User user) {
        log.debug("updateUser");
        em.merge(user);
        em.flush();
        return user;
    }
}
