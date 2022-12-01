package org.EuLife.dao;

import org.EuLife.Exceptions.DAO_Exception;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAO {

    private static final String PERSISTENCE_UNIT_NAME = "User";

    public EntityManagerFactory getEntityManagerFactory() throws DAO_Exception {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            return factory;
        } catch (Exception ex) {
            throw new DAO_Exception(ex.getMessage());
        }
    }


    public void saveEntity(Object obj) throws DAO_Exception {
        try {
            EntityManager em = getEntityManagerFactory().createEntityManager();
            Transaction tx = (Transaction) em.getTransaction();
            tx.begin();
            em.persist(obj);
            tx.commit();
            em.close();
        } catch (Exception ex) {
            throw new DAO_Exception(ex.getMessage());
        }
    }
}
