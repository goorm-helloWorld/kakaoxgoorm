<%--
  Created by IntelliJ IDEA.
  User: youngkwang
  Date: 2024. 9. 5.
  Time: 오후 5:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>quiz</title>
</head>
<body>
<h1>Quiz</h1>
  <form action="/quiz" method="post">
    <p> 문제 : ${question} </p>
    답을 입력하시오 : <input type="text" name="answer" required/> <br>
    <button type="submit">제출</button>
  </form>
</body>
</html>
