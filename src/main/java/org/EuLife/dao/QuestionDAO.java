package org.EuLife.dao;

import org.EuLife.Exceptions.DAO_Exception;
import org.EuLife.dao.Entities.Question;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class QuestionDAO extends DAO {

    public List<Question> selectQuestions() throws DAO_Exception {
        try {
            EntityManager em = getEntityManagerFactory().createEntityManager();
            List<Question> questionList = em.createQuery("select u from Question u").getResultList();
            em.close();
            return questionList;
        } catch (Exception ex) {
            throw new DAO_Exception(ex.toString());
        }
    }

    public Question findQuestionById(Long id) throws DAO_Exception {
        try {
            EntityManager em = getEntityManagerFactory().createEntityManager();
            Question question = em.find(Question.class, id);
            em.close();
            return question;
        } catch (Exception ex) {
            throw new DAO_Exception(ex.getMessage());
        }
    }

    public List<Question> findQuestionsByCriteria() throws DAO_Exception {
        try {
            EntityManager em = getEntityManagerFactory().createEntityManager();
            Session session = em.unwrap(Session.class);
            CriteriaBuilder cb = em.getCriteriaBuilder();
            List<Question> questionList = em.createQuery("select u from Question u").getResultList();
            em.close();
            return questionList;
        } catch (Exception ex) {
            throw new DAO_Exception(ex.toString());
        }
    }

}
