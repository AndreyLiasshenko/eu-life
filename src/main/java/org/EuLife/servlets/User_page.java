package org.EuLife.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.EuLife.Exceptions.DAO_Exception;
import org.EuLife.dao.Entities.Image;
import org.EuLife.dao.Entities.User;
import org.EuLife.dao.ImageDAO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "User", value = "/user")
@MultipartConfig
public class User_page extends HttpServlet {
    ImageDAO imageDAO = new ImageDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            request.getRequestDispatcher("/authorization.jsp").forward(request, response);
        }
        request.getRequestDispatcher("/user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Getting an image file from the user's form
        Part part = request.getPart("file");
        InputStream inputStream = part.getInputStream();

        // Converting the image from file to byte array
        BufferedImage bImage = ImageIO.read(inputStream);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos );
        byte [] data = bos.toByteArray();

        // Getting the user's session
        HttpSession session = request.getSession();

        // Getting the user's data from the session
        User user = (User) session.getAttribute("user");

        // Updating the user's image to the database
        try {
            imageDAO.updateImage(data, user.getId());
        } catch (DAO_Exception e) {
            Image saveImage = new Image();
            saveImage.setHeight(bImage.getHeight());
            saveImage.setFileName(part.getName());
            saveImage.setTitle("Users avatar");
            saveImage.setWidth(bImage.getWidth());
            saveImage.setImage(data);
            saveImage.setUser(user);

            try {
                imageDAO.saveEntity(saveImage);
            } catch (DAO_Exception ex) {
                request.setAttribute("cause", ex.getMessage());
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        }

        // Sending the page to the user again
        doGet(request, response);
    }

}

