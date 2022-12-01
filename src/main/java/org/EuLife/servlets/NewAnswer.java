package org.EuLife.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.EuLife.Exceptions.DAO_Exception;
import org.EuLife.dao.AnswerDAO;
import org.EuLife.dao.Entities.Answer;
import org.EuLife.dao.Entities.Question;
import org.EuLife.dao.Entities.User;
import org.EuLife.dao.QuestionDAO;

import java.io.IOException;
import java.util.Date;

@WebServlet(name = "NewAnswer", value = "/new-answer")
public class NewAnswer extends HttpServlet {
    QuestionDAO questionDAO = new QuestionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // getting data from request
        String question_id = request.getParameter("question_id"); // this need to now which page forwarding
        String text = request.getParameter("text");

        try {
            //getting a question instance from the database
            Question question = questionDAO.findQuestionById(Long.parseLong(question_id));

            // getting user's instance from session
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            // creation a new answer
            Answer answer = new Answer();
            answer.setText(text);
            answer.setTime(new Date());
            answer.setQuestion(question);
            answer.setUser(user);

            // persisting an answer
            AnswerDAO answerDAO = new AnswerDAO();
            answerDAO.saveEntity(answer);

        } catch (DAO_Exception ex) {
            // setting cause as request attribute
            request.setAttribute("cause", ex.getMessage());

            // forwarding to error page
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }

        // forwarding to the same page again
        request.setAttribute("question_id", question_id);
        request.getRequestDispatcher("/question.jsp").forward(request, response);
    }
}
