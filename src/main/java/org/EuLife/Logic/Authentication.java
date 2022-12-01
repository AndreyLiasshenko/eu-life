package org.EuLife.Logic;

import org.EuLife.dao.Entities.User;
import org.EuLife.dao.UserDAO;
import org.EuLife.utils.Security;

public class Authentication {
    public static int doAuthentication(String login, String password) {
        UserDAO userDAO = new UserDAO();
        int code = 0;
        try {

            // Finding user by login
            User user = userDAO.findUserByLogin(login);
            if (user != null) {
                // Getting users password and salt
                String users_pass = user.getPassword();
                String salt = user.getSalt();
                if (users_pass.equals(Security.getHash(password + salt))) return 0;
                else code = 1;
            } else {
                // If a user is null
                code = 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return code;
    }
}
