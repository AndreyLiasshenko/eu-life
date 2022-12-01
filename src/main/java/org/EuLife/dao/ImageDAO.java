package org.EuLife.dao;

import org.EuLife.Exceptions.DAO_Exception;
import org.EuLife.dao.Entities.Image;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;

public class ImageDAO extends DAO {

    public void updateImage(byte[] binaryImg, Long user_id) throws DAO_Exception {
        EntityManager em = getEntityManagerFactory().createEntityManager();
        try {
            Transaction tx = (Transaction) em.getTransaction();
            tx.begin();
            Image image = (Image) em.createQuery("select i from Image i where user_id=:id")
                    .setParameter("id",user_id)
                    .getSingleResult();
            image.setImage(binaryImg);
            tx.commit();
        } catch (Exception ex) {
            throw new DAO_Exception(ex.getMessage());
        } finally {
            em.close();
        }
    }
}
