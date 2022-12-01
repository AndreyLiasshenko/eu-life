package org.EuLife.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.EuLife.Exceptions.DAO_Exception;
import org.EuLife.dao.Entities.Question;
import org.EuLife.dao.QuestionDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Index", value = "/")
public class Index extends HttpServlet {
    QuestionDAO questionDAO = new QuestionDAO();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Question> questionList = questionDAO.selectQuestions();
            request.setAttribute("questionsList", questionList);
            request.getRequestDispatcher("/index.jsp").forward(request, response);

        } catch (DAO_Exception ex) {
            ex.printStackTrace();
            request.setAttribute("cause", ex.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
