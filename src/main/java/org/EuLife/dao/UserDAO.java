package org.EuLife.dao;

import org.EuLife.Exceptions.DAO_Exception;
import org.EuLife.dao.Entities.User;
import org.EuLife.dao.enums.Role;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import java.util.List;

public class UserDAO extends DAO {

    public User findUserById(Long id) throws DAO_Exception {
        try {
            EntityManager em = getEntityManagerFactory().createEntityManager();
            User user = em.find(User.class, id);
            em.close();
            return user;
        } catch (Exception ex) {
            throw new DAO_Exception(ex.getMessage());
        }
    }

    public User findUserByLogin(String login) throws DAO_Exception {
        EntityManager em = getEntityManagerFactory().createEntityManager();
        try {

            User user = (User) em.createQuery("select u from User u where login=:login")
                    .setParameter("login", login)
                    .getSingleResult();
            return user;

        } catch (Exception ex) {
            throw new DAO_Exception(ex.getMessage());
        } finally {
            em.close();
        }
    }

}
