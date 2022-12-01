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

        <div class="wrapper">

            <div class="head">
                <custom:logOrReg/>
            </div>

            <div id="contentWrapper">

                <div class="navbar">
                    <custom:navbar/>
                </div>

                <div id="logInBox">
                    <h4>Авторизація</h4>
                    <h4 style="color: red">
                        <%
                            String cause = (String) request.getAttribute("cause");
                            if (cause != null) out.println("<p>" + cause + "</p>");
                        %>
                    </h4>
                    <div id="logInInput">
                        <form method="post" action="authorization">
                            <h5>Логін</h5>
                            <label>
                                <input type="text" class="input" name="users_login">
                            </label>

                            <h5>Пароль</h5>
                            <label>
                                <input type=password class="input" name="users_password">
                            </label>

                            <label>
                                <input type="submit" class="button" value="Ok">
                            </label>

                            <a href="${pageContext.request.contextPath}/registration" id="reg">
                                Ще не зареєстровані? Натисніть тут для реєстрації.
                            </a>
                        </form>
                    </div>
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
