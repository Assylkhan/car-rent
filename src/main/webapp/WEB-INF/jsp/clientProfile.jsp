<%--
  Created by IntelliJ IDEA.
  User: Асылхан
  Date: 19.01.2015
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel='stylesheet' href='<c:url value="/webjars/bootstrap/3.3.1/css/bootstrap.css"/>'>
    <title>Profile</title>
</head>
<body>
<%@include file="header.jspf" %>
<div class="container">
    <p>${insertApp}</p><br/>

    <p class="btn btn-danger">first name: ${client.firstName}</p>

    <p>last name: ${client.lastName}</p>

    <p>login: ${client.login}</p>

    <p>password: ${client.password}</p>
    <br/>

    <p>
        <strong><a href="${pageContext.request.contextPath}/do/sendApp">
            application
        </a></strong>
    </p>
</div>
</body>
</html>
