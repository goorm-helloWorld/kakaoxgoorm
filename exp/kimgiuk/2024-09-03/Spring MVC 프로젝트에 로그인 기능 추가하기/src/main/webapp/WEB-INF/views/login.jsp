<%--
  Created by IntelliJ IDEA.
  User: giuk
  Date: 2024. 9. 3.
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
    <title>Login</title>
</head>
<body>
  <h1>Login</h1>
  <form:form modelAttribute="user" action="login" method="post">
    이메일 : <input type="text" name="email"/><br>
    비밀번호 : <input type="password" name="password"/><br>
    <input type="submit" value="로그인" />
  </form:form>

  <h2>${message}</h2>
</body>
</html>
