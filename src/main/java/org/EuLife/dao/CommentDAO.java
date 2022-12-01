package org.EuLife.dao;

import org.EuLife.Exceptions.DAO_Exception;
import org.EuLife.dao.Entities.Comment;

import javax.persistence.EntityManager;

public class CommentDAO extends DAO {

    public Comment findCommentById(Long id) throws DAO_Exception {
        try {
            EntityManager em = getEntityManagerFactory().createEntityManager();
            Comment comment = em.find(Comment.class, id);
            em.close();
            return comment;
        } catch (Exception ex) {
            throw new DAO_Exception(ex.getMessage());
        }
    }

}
