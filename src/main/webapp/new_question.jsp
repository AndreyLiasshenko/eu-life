<%@ page import="org.EuLife.dao.Entities.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="mytags" prefix="custom"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>EU Life</title>
    <link href="WEB-INF/assets/css/style.css" rel="stylesheet" type="text/css">
    <link href="WEB-INF/assets/css/log-in_registration_style.css" rel="stylesheet" type="text/css">
    <link href="WEB-INF/assets/css/new_question_style.css" rel="stylesheet" type="text/css">
  </head>
  <body>
      <style>
        <%@include file="WEB-INF/assets/css/style.css" %>
        <%@include file="WEB-INF/assets/css/log-in_registration_style.css"%>
        <%@include file="WEB-INF/assets/css/new_question_style.css" %>
      </style>

      <%
        User user = (User) session.getAttribute("user");
        String imagePath = null;
        if (user != null)  imagePath = request.getContextPath() + "/image?user_id=" + user.getId().toString();
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
          <form method="post" action="new-question">

            <h4>Тема запитання</h4>
            <label><textarea maxlength="1000" name="topic" class="main_text topic"></textarea></label>

            <h4 style="margin-top: 8px;">Текст</h4>
            <label><textarea maxlength="10000" name="main_text" class="main_text"></textarea></label>

            <h4  style="margin-top: 5px">Теги</h4>
            <h4 style="font-size: 12px">(через пробіл)</h4>
            <label><input type="text" maxlength="50" name="tags" id="tags"></label>

            <hr style="border: none">
            <input type="submit" class="button" value="Відправити" name="Ok" id="submit">

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

  </body>
</html>