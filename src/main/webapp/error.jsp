<%@ page import="org.EuLife.dao.Entities.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="mytags" prefix="custom"%>

<html>
    <head>
        <title>EU-Life</title>
    </head>

    <body>
        <style>
            <%@include file="WEB-INF/assets/css/style.css" %>
            <%@include file="WEB-INF/assets/css/log-in_registration_style.css"%>
        </style>

        <%
            User user = (User) session.getAttribute("user");
            String imagePath = request.getContextPath() + "/image?user_id=" + user.getId().toString();
            pageContext.setAttribute("user", user);
            pageContext.setAttribute("imagePath", imagePath);
        %>

        <div class="wrapper">

            <div class="head">
                <custom:logOrReg/>
            </div>

            <div id="contentWrapper">

                <div class="navbar">
                    <custom:navbar/>
                </div>

                <div id="content">
                    <h4 style="color: red">
                        ПОМИЛКА
                        Зв'яжіться з системним адміністратором
                        <%
                            String cause = (String) request.getAttribute("cause");
                            out.println("<p>" + cause + "</p>");
                        %>
                    </h4>
                </div>

            </div>

            <div class="footer">
                <hr>
                <div id="footerText">
                    <h4>Про сайт</h4>
                    <h4>Email</h4>
                </div>
            </div>

        </div>
    </body>
</html>