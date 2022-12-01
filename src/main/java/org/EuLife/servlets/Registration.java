package org.EuLife.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.EuLife.dao.Entities.Image;
import org.EuLife.dao.Entities.User;
import org.EuLife.dao.ImageDAO;
import org.EuLife.dao.UserDAO;
import org.EuLife.dao.enums.Role;
import org.EuLife.utils.Security;

import java.io.IOException;
import java.util.Date;


@WebServlet(name = "Registration", value = "/registration")
public class Registration extends HttpServlet {
    ImageDAO imageDAO = new ImageDAO();
    UserDAO userDAO = new UserDAO();
    User newUser = new User();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/registration.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");

        //getting users data from request
        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        try {
            newUser.setFirst_name(request.getParameter("firstName"));
            newUser.setLast_name(request.getParameter("lastName"));
            newUser.setAge(Integer.parseInt(request.getParameter("age")));
            newUser.setLogin(request.getParameter("login"));
            newUser.setDate_of_creation(new Date());
            newUser.setRole(Role.USER);

            // generation a new salt for user's password
            String salt = Security.generateSalt();

            //creation a new password
            String newPass = Security.getHash(pass + salt);

            newUser.setPassword(newPass);
            newUser.setSalt(salt);

            // saving Entity
            userDAO.saveEntity(newUser);


            // getting a default image entity from the template user (id=21)
            User def_user = userDAO.findUserById(21L);

            User reg_user = userDAO.findUserByLogin(login);

            Image image = new Image();
            image.setImage(def_user.getImage().getImage());
            image.setFileName("Image.jpg");
            image.setWidth(def_user.getImage().getWidth());
            image.setHeight(def_user.getImage().getHeight());
            image.setTitle("avatar");
            image.setUser(reg_user);

            //setting default image for new user
            imageDAO.saveEntity(image);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("cause", "EXCEPTION: " + ex.getCause());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }

        request.getRequestDispatcher("/authorization.jsp").forward(request, response);
    }
}
