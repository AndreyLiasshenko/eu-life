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
            <%@include file="WEB-INF/assets/css/user_page.css"%>
        </style>

        <%
            User user = (User) session.getAttribute("user");
            String imagePath = request.getContextPath() + "/image?user_id=" + user.getId().toString();
            pageContext.setAttribute("user", user);
            pageContext.setAttribute("imagePath", imagePath);
        %>

    <div class="wrapper">
        <div class="head">
            <custom:logOrReg logined="${user != null}" username="${user.fullName}" imagepath="${imagePath}"/>
        </div>

        <div id="contentWrapper">

            <div class="navbar">
                <custom:navbar/>
            </div>

            <div id="content">
                <div style="width: 210px; display: inline-block">
                    <img src="<%= imagePath %>" width="200" height="200" alt="image">
                    <h4>Змінити зоображення</h4>
                    <form method="post" enctype="multipart/form-data" action="user" style="height: 100px">
                        <input type="file" name="file" style="display: block">
                        <input type="submit" class="button" value="Змінити">
                    </form>
                </div>
                <div style="width: 210px; display: inline-block" id="users_data">
                    <h4>Ім'я: <%= user.getFirst_name() %></h4>
                    <h4>Фамілія: <%= user.getLast_name() %></h4>
    <%--                <h4>Група: 222/2</h4>--%>
    <%--                <h4>Курс: 2</h4>--%>
    <%--                <h4>Факультет: ФІСТ</h4>--%>
                    <h4>Дата створення акаунта: <%=user.getDate_of_creation().toString()%></h4>
    <%--                <h4>Рівень: 10</h4>--%>
                </div>

                <div style="width: 210px; display: inline-block">
                    <form action="${pageContext.request.contextPath}/upd-user-data" method="post">
                        Змінити групу
                        <input type="text" name="group" maxlength="6">
                        Змінити факультет
                        <input type="text" name="faculty" maxlength="25">
                        <input type="submit" class="button" value="Змінити">
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
