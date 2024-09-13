<%--
  Created by IntelliJ IDEA.
  User: giuk
  Date: 2024. 9. 5.
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false" %>

<html>
<head>
  <title>Quiz</title>
</head>
<body>
<h1>Quiz</h1>

<h3>Question: ${question.question}</h3>

<form action="/quiz" method="post">
  <label for="answer">Your answer:</label>
  <input type="text" id="answer" name="answer" required>
  <input type="submit" value="Submit">
</form>
</body>
</html>
