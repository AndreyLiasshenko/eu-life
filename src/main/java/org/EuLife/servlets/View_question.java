package org.EuLife.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Question", value = "/question")
public class View_question extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String method_of_sorting = request.getParameter("select_sorting");
        String id = request.getParameter("question_id");

        request.setAttribute("question_id", id);
        request.setAttribute("select_sorting", method_of_sorting);
        request.getRequestDispatcher("/question.jsp").forward(request, response);
    }

}
