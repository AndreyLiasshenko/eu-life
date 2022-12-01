package org.EuLife.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.EuLife.Exceptions.DAO_Exception;
import org.EuLife.dao.AnswerDAO;
import org.EuLife.dao.CommentDAO;
import org.EuLife.dao.Entities.Answer;
import org.EuLife.dao.Entities.Comment;
import org.EuLife.dao.Entities.User;

import java.io.IOException;
import java.util.Date;

@WebServlet(name = "NewComment", value = "/new-comment")
public class NewComment extends HttpServlet {

    AnswerDAO answerDAO = new AnswerDAO();
    CommentDAO commentDAO = new CommentDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String text = request.getParameter("text");
        String question_id = request.getParameter("question_id");
        String answer_id = request.getParameter("answer_id");
        String comment_id = request.getParameter("comment_id");

        Comment new_comment = new Comment();
        new_comment.setAuthor(user);
        new_comment.setTime(new Date());
        new_comment.setText(text);

        try {
            if (!answer_id.equals("null")) {
                Answer answer = answerDAO.findAnswerById(Long.parseLong(answer_id));
                new_comment.setAnswer(answer);

            } else {
                Comment comment = commentDAO.findCommentById(Long.parseLong(comment_id));
                new_comment.setComment(comment);
            }

            commentDAO.saveEntity(new_comment);

        } catch (DAO_Exception ex) {
        request.setAttribute("cause", ex.getMessage());
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }

        request.setAttribute("question_id", question_id);
        request.getRequestDispatcher("/question.jsp").forward(request, response);
    }
}
