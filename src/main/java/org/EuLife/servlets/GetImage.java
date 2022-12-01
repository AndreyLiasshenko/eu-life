package org.EuLife.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.EuLife.Exceptions.DAO_Exception;
import org.EuLife.dao.Entities.User;
import org.EuLife.dao.UserDAO;

import java.io.IOException;

@WebServlet("/image")
public class GetImage extends HttpServlet {
    UserDAO userDAO = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_id = request.getParameter("user_id");
        response.setContentType("image/jpg");
        try {
            User user = userDAO.findUserById(Long.parseLong(user_id));
            response.setContentLength(user.getImage().getImage().length);
            if (user.getImage() != null) {
                if (user.getImage().getImage() != null) {
                    response.getOutputStream().write(user.getImage().getImage());
                }
            } else {
                System.out.println("Image is empty");
            }

        } catch (DAO_Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
