<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/static/css/bootstrap.css">
    <link rel="stylesheet" href="/static/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/static/css/main.css">
    <title>Registration</title>
</head>
<body>
<%@ include file="header.jspf"%>
<div class="container-fluid">
    <form action="${pageContext.request.contextPath}/do/register" method="post">
        <strong style="color: darkred">${emailError}</strong> <br/>
        <div class="form-group"><input type="text" placeholder="email" name="email"/></div>
        <div class="form-group"><input type="password" placeholder="password" name="password"/></div>
        <div class="form-group"><input type="password" placeholder="confirm password" name="confirmPass"/></div>
        <strong style="color: darkred">${confirmError}</strong><br/>
        <div class="form-group"><button type="submit" class="btn btn-warning">Signup</button></div>
    </form>
</div>
</body>
</html>
