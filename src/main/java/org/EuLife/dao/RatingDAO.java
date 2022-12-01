package org.EuLife.dao;

import org.EuLife.Exceptions.DAO_Exception;
import org.EuLife.dao.Entities.Answer;
import org.EuLife.dao.Entities.Rating;
import org.EuLife.dao.enums.TypeOfLike;
import org.EuLife.dao.Entities.User;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;

public class RatingDAO extends DAO {

    public void changeRating(Long user, Long answer, String toValue) throws DAO_Exception {
        try  {
            EntityManager em = getEntityManagerFactory().createEntityManager();
            Transaction tx = (Transaction) em.getTransaction();
            tx.begin();
            em.createQuery("UPDATE Rating set type=:t where user_id=:user and  answer_id=:answer")
                    .setParameter("t", toValue)
                    .setParameter("user", user)
                    .setParameter("answer", answer)
                    .executeUpdate();
            tx.commit();
            em.close();
        } catch (Exception ex ) {
            ex.printStackTrace();
            throw new DAO_Exception(ex.getMessage());
        }
    }


    public void deleteRating(Long user, Long answer) throws DAO_Exception {
        try {
            EntityManager em = getEntityManagerFactory().createEntityManager();
            Transaction tx = (Transaction) em.getTransaction();
            tx.begin();
            em.createQuery("delete from  Rating WHERE user_id=:user and answer_id=:answer")
                    .setParameter("user", user)
                    .setParameter("answer", answer).executeUpdate();
            tx.commit();
            em.close();
        } catch (Exception ex) {
            throw new DAO_Exception(ex.getMessage());
        }
    }

    public void setLike (Long user_id, Long answer_id, TypeOfLike type) throws DAO_Exception {
        try {
            EntityManager em = getEntityManagerFactory().createEntityManager();
            Transaction tx = (Transaction) em.getTransaction();
            tx.begin();

            User user = em.find(User.class, user_id);
            Answer answer = em.find(Answer.class, answer_id);
            System.out.println(answer.getTime());
            Rating rating = new Rating();
            rating.setUser(user);
            rating.setAnswer(answer);
            rating.setType(type);
            em.persist(rating);

            tx.commit();
            em.close();
        } catch (Exception ex) {
            throw new DAO_Exception(ex.getMessage());
        }
    }
}
