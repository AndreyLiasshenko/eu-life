<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="org.EuLife.dao.*" %>
<%@ page import="org.EuLife.dao.Entities.*" %>
<%@ page import="org.EuLife.dao.enums.TypeOfLike" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="mytags" prefix="custom"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>EU Life</title>
    <link rel="shortcut icon" href="logo.svg" type="image/x-icon">
  </head>
  <body>

    <style>
      <%@include file="WEB-INF/assets/css/style.css" %>
      <%@include file="WEB-INF/assets/css/log-in_registration_style.css"%>
      <%@include file="WEB-INF/assets/css/question_page_style.css" %>
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

      <%
        QuestionDAO questionDAO = new QuestionDAO();
        AnswerDAO answerDAO = new AnswerDAO();

        String id = request.getParameter("question_id");
        Question question = questionDAO.findQuestionById(Long.parseLong(id));
        pageContext.setAttribute("question", question);
        User author = question.getAuthor();
        pageContext.setAttribute("author", author);
      %>

      <div id="content">
        <h2 id="title">${question.topic}</h2>
        <div class="question_class" id="question_info">
          <h5 style="text-align: right">${question.time.toString().substring(0,10)}</h5>
          <img src="${pageContext.request.contextPath}/image?user_id=${author.id}" width="40" height="40" alt="user's avatar">
          <h5 class="users_info">${author.fullName}</h5>
          <h4>${question.text}</h4>
        </div>

        <form action="${pageContext.request.contextPath}/question" method="get">
          <label for="select_sorting" style="font-size: 22px; font-weight: bold">Відповіді</label>
          <select name="select_sorting" id="select_sorting">
            <option value="by_rating">Найвищий рейтинг</option>
            <option value="in_descending_order">Спочатку новіші</option>
            <option value="in_ascending_order">Спочатку старіші</option>
          </select>
          <input type="text" name="question_id" value="${question.id}" style="display: none">
          <input type="submit" value="Ok" class="button" style="margin: 0; width: 30px">
        </form>

        <%
          List<Answer> answerList = question.getAnswerList();
          String sort_by = request.getParameter("select_sorting");
          System.out.println(sort_by);
          if (sort_by != null) {
            if (sort_by.equals("in_descending_order")) {
              answerList.sort(Answer.SORT_IN_DESCENDING_ORDER);
            } else if (sort_by.equals("by_rating")) {
              answerList.sort(Answer.SORT_BY_RATING);
            }
          } else {
            answerList.sort(Answer.SORT_BY_RATING);
          }

          pageContext.setAttribute("answList1", answerList);
          %>

          <c:forEach var="answer" items="${answList1}">
            <%
              Answer answer = (Answer) pageContext.getAttribute("answer");
              String methodLike="", methodDisLike="";
              if (user != null) {
                TypeOfLike type = answerDAO.getRatingOfAnswerByUserId(user.getId(), answer.getId());

                Long user_id = user.getId();
                Long answ_id = answer.getId();
                if (type != null) {

                  if (type == TypeOfLike.LIKE){
                    methodLike = "updateRating( '"+answ_id+"', '"+user_id+"', 'LIKE', 'DELETE' )";
                    methodDisLike = "updateRating( '"+answ_id+"', '"+user_id+"', 'DISLIKE', 'PUT' )";
                  }

                  if (type == TypeOfLike.DISLIKE) {
                    methodLike ="updateRating( '"+answ_id+"', '"+user_id+"', 'LIKE', 'PUT' )";
                    methodDisLike = "updateRating( '"+answ_id+"', '"+user_id+"', 'DISLIKE', 'DELETE' )";

                  }
                } else {
                  methodLike = "updateRating( '"+answ_id+"', '"+user_id+"', 'LIKE', 'POST' )";
                  methodDisLike = "updateRating( '"+answ_id+"', '"+user_id+"', 'DISLIKE', 'POST' )";
                }

              } else {
                methodLike = "alert('log-in please')";
                methodDisLike = "alert('log-in please')";
              }
              pageContext.setAttribute("methodLike" , methodLike);
              pageContext.setAttribute("methodDisLike" , methodDisLike);
            %>

          <div class="question_wrapper">
            <div class="arrow_button_wrapper" style="width: 40px; height: 65px; float: left; margin-bottom: 20px">
              <button class="arrow_button" onclick="${methodLike}" >
                <svg width="40" height="20"><g><path class="triangle" d="M20 0, 0 20, 40 20"></path></g></svg>
              </button>
              <span class="score">${answer.rating}</span>
              <button class="arrow_button" onclick="${methodDisLike}">
                <svg width="40" height="20"><g><path class="triangle" d="M0 0, 20 20, 40 0"></path></g></svg>
              </button>
            </div>
            <div class="question_class answer">
              <h5 class="time">${answer.time.toString().substring(0,10)}</h5>
              <img src="${pageContext.request.contextPath}/image?user_id=${answer.user.id}" width="40" height="40" alt="user's avatar">
              <h5 class="users_info">${answer.user.fullName}</h5>
              <h4>${answer.text}</h4>
              <custom:comment islogined="<%=user != null%>" answerId="${answer.id}" questionId="${question.id}" />
                <hr class="answer_hr">
              </div>
            </div>

            <c:if test="${answer.commentList != null}">
              <c:forEach var="comment" items="${answer.commentList}">
                <div class="question_class answer comment" style="margin-left: 100px">
                  <h5 class="time">${comment.time.toString().substring(0,10)}</h5>
                  <img src="${pageContext.request.contextPath}/image?user_id=${comment.author.id}" width="40" height="40" alt="user's avatar">
                  <h5 class="users_info">${comment.author.fullName}</h5>
                  <h4>${comment.text}</h4>
                  <custom:comment islogined="${user != null}" commentId="${comment.id}" questionId="${question.id}"/>
                  <hr class="answer_hr">
                </div>

                <c:if test="${comment.commentList != null}">
                  <c:forEach var="comment2" items="${comment.commentList}">
                  <div class="question_class answer comment" style="margin-left: 150px">
                    <h5 class="time">${comment2.time.toString().substring(0,10)}</h5>
                    <img src="${pageContext.request.contextPath}/image?user_id=${comment2.author.id}" width="40" height="40" alt="user's avatar">
                    <h5 class="users_info">${comment2.author.fullName}</h5>
                    <h4>${comment2.text}</h4>
                    <custom:comment islogined="${user != null}" commentId="${comment2.id}" questionId="${question.id}" />
                    <hr class="answer_hr">
                  </div>

                    <c:if test="${comment2.commentList != null}">
                      <c:forEach var="comment3" items="${comment2.commentList}">
                        <div class="question_class answer comment" style="margin-left: 200px">
                          <h5 class="time">${comment3.time.toString().substring(0,10)}</h5>
                          <img src="${pageContext.request.contextPath}/image?user_id=${comment3.author.id}" width="40" height="40" alt="user's avatar">
                          <h5 class="users_info">${comment3.author.fullName}</h5>
                          <h4>${comment3.text}</h4>
                          <hr class="answer_hr">
                        </div>
                      </c:forEach>
                    </c:if>

                  </c:forEach>
                </c:if>

              </c:forEach>
            </c:if>

          </c:forEach>

        <div class="question_class answer reply">
          <h3>Ваша відповідь</h3>

          <div>

            <form action="new-answer?question_id=<%=question.getId().toString()%>" method="post">
              <label>
                <textarea name="text" class="text_reply" cols="30" rows="10" maxlength="5000" ></textarea>
              </label>

              <c:if test="${user != null}">
                <input type="submit" class="button" style="display: block" value="Відповісти">
              </c:if>

              <c:if test="${user == null}">
                <input type="submit" class="disButton" style="display: block" value="Відповісти" disabled>
              </c:if>

            </form>
          </div>
        </div>


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

    <script type="text/javascript">
      <%@include file="/WEB-INF/assets/js/getOrUpdateLikes.js"%>
      <%@include file="/WEB-INF/assets/js/app.js"%>
    </script>
  </body>

</html>