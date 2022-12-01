<%@ page import="java.util.List" %>
<%@ page import="org.EuLife.dao.Entities.Question" %>
<%@ page import="org.EuLife.dao.Entities.Question" %>
<%@ page import="org.EuLife.dao.Entities.User" %>
<%@ page import="org.EuLife.Exceptions.DAO_Exception" %>
<%@ page import="org.EuLife.dao.QuestionDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="mytags" prefix="custom"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>EU-Life</title>
    </head>
    <body>
        <style>
            <%@include file="WEB-INF/assets/css/style.css" %>
            <%@include file="WEB-INF/assets/css/index_style.css" %>
            <%@include file="WEB-INF/assets/css/log-in_registration_style.css" %>
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
                    <%
                    QuestionDAO questionDAO = new QuestionDAO();
                    try {
                       List<Question> questionList = questionDAO.selectQuestions();
                       pageContext.setAttribute("questionList", questionList);
                    %>
                    <c:if test="${questionList != null}" >
                        <c:forEach var="q" items="${questionList}">
                            <div class="question_class">
                                <a class="invisible_link" style="font-size: 22px" href=${pageContext.request.contextPath}/question?question_id=${q.id}> ${ q.topic }
                                    <span class="testing"> ${q.text} </span>
                                </a>
                            </div>
                        </c:forEach>
                    </c:if>

                    <c:if test="${questionList == null}" >
                        <h4>There are no question</h4>
                    </c:if>

                    <%
                    } catch (DAO_Exception ex) {
                        out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                    %>

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
