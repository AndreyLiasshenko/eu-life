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
                    <ul>
                        <custom:navbar/>
                    </ul>
                </div>

                <div id="logInRegBox">
                    <h4>Реєстрація</h4>
                    <div id="logInReg">
                        <form method="post" action="registration">
                            <h5>Ваше ім'я</h5>
                            <label><input type="text" class="input" id="inputFirstName" name="firstName"></label>

                            <h5>Фамілія</h5>
                            <label><input type="text" class="input" id="inputLastName" name="lastName"></label>

                            <h5>Вік</h5>
                            <label><input type="number" class="input" id="age" name="age"></label>

                            <h5>Email (ваш логін)</h5>
                            <label><input type="text" class="input" name="login"></label>

                            <h5 style="margin-top: 20px">Пароль</h5>
                            <label><input type=text name="password" class="input"></label>

                            <h5>Підтверження пароля</h5>
                            <label><input type=text name="password2" class="input"> </label>

                            <h5>Країна, якій належить півострів Крим</h5>
                            <input type=text id="orientation" class="input">

                            <input type="submit" class="button" value="Ok" name="Ok">
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
