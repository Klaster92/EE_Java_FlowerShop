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
        try {
            TypedQuery<User> query;
            query = em.createQuery("SELECT e from User e", User.class);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public User getUserByLoginAndPassword(String login, String password) {
        try{
            TypedQuery<User> query = em.createQuery
                    ("select u from User u where u.login=:login and "
                            + "u.password=:password", User.class);
            query.setParameter("login", login);
            query.setParameter("password", password);
            User user = query.getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User addUser(User user) {
        try{
            em.persist(user);
            em.flush();
            return user;
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public void deleteUser(User user) {
            em.remove(user);
            em.flush();
    }

    @Override
    public User updateUser(User user) {
        try {
            em.refresh(user);
            em.flush();
            return user;
        }catch (NoResultException e) {
            return null;
        }

    }
}
