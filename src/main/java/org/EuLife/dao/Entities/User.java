package org.EuLife.dao.Entities;

import org.EuLife.dao.enums.Role;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users_data")
public class User {
    public User() {    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String first_name;

    private String last_name;

    private int age;

    private String login;

    private String password;

    private String salt;

    private Date date_of_creation;


    @OneToOne(mappedBy = "user" )
    private Image image;

    @Enumerated(EnumType.STRING)
    private Role role;

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getLogin() {
        return login;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public String getFullName() {
        return first_name + " " + last_name;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getDate_of_creation() {
        return date_of_creation;
    }

    public void setDate_of_creation(Date date_of_creation) {
        this.date_of_creation = date_of_creation;
    }

    @Override
    public String toString() {
        return "First_name='" + first_name + '\'' +
                "  Last_name='" + last_name + '\'' +
                "  Age=" + age +
                "Password= " + password;
    }
}