package org.EuLife.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.EuLife.Exceptions.DAO_Exception;
import org.EuLife.dao.Entities.Question;
import org.EuLife.dao.Entities.User;
import org.EuLife.dao.QuestionDAO;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "Newquestion", value = "/new-question")
public class Newquestion extends HttpServlet {

    QuestionDAO questionDAO = new QuestionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            request.setCharacterEncoding("UTF-8");
            request.getRequestDispatcher("/new_question.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/authorization.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String topic = request.getParameter("topic");
        String text = request.getParameter("main_text");
        String tags =  request.getParameter("tags");

        Question question = new Question();
        question.setTime(new Date());
        question.setAuthor(user);
        question.setTopic(topic);
        question.setText(text);
        question.setTags(tags);

        try {
            questionDAO.saveEntity(question);
            List<Question> questionList = questionDAO.selectQuestions();

            request.setAttribute("questionsList", questionList);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (DAO_Exception ex) {
            request.setAttribute("cause", ex.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }


    }
}
