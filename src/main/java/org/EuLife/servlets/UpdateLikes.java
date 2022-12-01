package org.EuLife.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.EuLife.Exceptions.DAO_Exception;
import org.EuLife.dao.enums.TypeOfLike;
import org.EuLife.dao.RatingDAO;

import java.io.IOException;

@WebServlet(name = "UpdateLikes", value = "/update-likes")
public class UpdateLikes extends HttpServlet {
    RatingDAO ratingDAO = new RatingDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long user_id = Long.parseLong(request.getParameter("user_id"));
        Long answer_id = Long.parseLong(request.getParameter("answer_id"));

        String likeOrDislike = (String) request.getParameter("likeOrDislike");
        System.out.println("Update likes: user: " + user_id +"answ: " + answer_id + "op" + likeOrDislike);

        try {

            if (likeOrDislike.equals("LIKE")) {

                System.out.println("LIKE");
                ratingDAO.setLike(user_id, answer_id , TypeOfLike.LIKE);

            } else if (likeOrDislike.equals("DISLIKE")) {
                ratingDAO.setLike(user_id, answer_id , TypeOfLike.DISLIKE);
            }

        } catch (DAO_Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long user_id = Long.parseLong(req.getParameter("user_id"));
        Long answer_id = Long.parseLong(req.getParameter("answer_id"));

        try {
            ratingDAO.deleteRating(user_id, answer_id);
        } catch (DAO_Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long user_id = Long.parseLong(req.getParameter("user_id"));
        Long answer_id = Long.parseLong(req.getParameter("answer_id"));
        String likeOrDislike = (String) req.getParameter("likeOrDislike");
        try {
            ratingDAO.changeRating(user_id, answer_id, likeOrDislike);
        } catch (DAO_Exception e) {
            throw new RuntimeException(e);
        }
    }

}
