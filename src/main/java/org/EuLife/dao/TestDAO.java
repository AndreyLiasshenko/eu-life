package org.EuLife.dao;


import org.EuLife.Exceptions.DAO_Exception;
import org.EuLife.dao.Entities.User;
import org.EuLife.dao.enums.Role;
import org.EuLife.utils.Security;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class TestDAO extends DAO{





    public static void main(String[] args) throws DAO_Exception {
        UserDAO userDAO = new UserDAO();
//        String pass = "12345";
//        User newUser = new User();
//        newUser.setFirst_name("test");
//        newUser.setLast_name("ltest");
//        newUser.setAge(19);
//        newUser.setLogin("123");
//        newUser.setDate_of_creation(new Date());
//        newUser.setRole(Role.USER);
//
//        String salt = Security.generateSalt();
//
//        //creation a new password
//        String newPass = Security.getHash(pass + salt);
//
//        newUser.setPassword(newPass);
//        newUser.setSalt(salt);
//
//        // saving Entity
//        userDAO.saveEntity(newUser);

    }
}

