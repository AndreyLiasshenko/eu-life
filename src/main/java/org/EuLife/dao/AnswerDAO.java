package org.EuLife.dao;

import org.EuLife.Exceptions.DAO_Exception;
import org.EuLife.dao.Entities.Answer;
import org.EuLife.dao.Entities.Rating;
import org.EuLife.dao.enums.TypeOfLike;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class AnswerDAO extends DAO {

    public Answer findAnswerById(Long id) throws DAO_Exception {
        try {
            EntityManager em = getEntityManagerFactory().createEntityManager();
            Answer answer = em.find(Answer.class, id);
            em.close();
            return answer;
        } catch (Exception ex) {
            throw new DAO_Exception(ex.getMessage());
        }
    }

    public TypeOfLike getRatingOfAnswerByUserId (Long user, Long answer) throws DAO_Exception {
        try {
            EntityManager em = getEntityManagerFactory().createEntityManager();

            Rating rating = (Rating) em.createQuery("FROM Rating where answer_id=:answer AND user_id=:user")
                    .setParameter("user", user)
                    .setParameter("answer", answer)
                    .getSingleResult();
            return rating.getType();
        } catch (NoResultException ex) {
            return null;
        } catch (Exception ex) {
            throw new DAO_Exception(ex.getMessage());
        }
    }

}
