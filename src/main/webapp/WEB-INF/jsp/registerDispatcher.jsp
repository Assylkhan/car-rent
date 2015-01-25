<%--
  Created by IntelliJ IDEA.
  User: Асылхан
  Date: 25.01.2015
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dispathcer registration</title>
</head>
<body>
<%@ include file="header.jspf"%>
<div class="container-fluid">
  <form action="${pageContext.request.contextPath}/do/register" method="post">
    <input type="hidden" name="role" value="DISPATCHER"/>
    <div class="form-group"><input type="text" placeholder="first name" name="firstName"/><br/><small>${firstNameError}</small></div>
    <div class="form-group"><input type="text" placeholder="last name" name="lastName"/><h6>${lastNameError}</h6></div>
    <div class="form-group"><input type="text" placeholder="national id" name="nationalId"/><h6>${natIdError}</h6></div>
    <div class="form-group"><input type="text" placeholder="phone" name="phone"/></div>
    <div class="form-group"><input type="text" placeholder="login" name="login"/><h6>${loginError}</h6></div>
    <div class="form-group"><input type="password" placeholder="password" name="password"/><h6>${passwordError}</h6></div>
    <div class="form-group"><input type="password" placeholder="confirm password" name="confirmPass"/><h6>${confirmError}</h6></div>
    <div class="form-group"><button type="submit" class="btn btn-warning">Signup</button></div>
  </form>
</div>
</body>
</html>
