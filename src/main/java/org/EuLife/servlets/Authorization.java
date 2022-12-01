package org.EuLife.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.EuLife.Logic.Authentication;
import org.EuLife.dao.Entities.User;
import org.EuLife.dao.UserDAO;

import java.io.IOException;

@WebServlet(name = "Authorization", value = "/authorization")
public class Authorization extends HttpServlet {
    UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/authorization.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // getting password and login from request
        String login = request.getParameter("users_login");
        String password = request.getParameter("users_password");

        try {
            if (Authentication.doAuthentication(login, password) == 0) {
                // Authentication passed
                // Setting a user's instance as session attribute
                HttpSession session = request.getSession();
                User user = userDAO.findUserByLogin(login);
                session.setAttribute("user", user);

                // forwarding to start page
                request.getRequestDispatcher("/index.jsp").forward(request, response);

            } else {
                // Authentication wasn't pass
                // setting cause as attribute to request
                request.setAttribute("cause", "Невірний логін чи пароль");
                request.getRequestDispatcher("/authorization.jsp").forward(request, response);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
