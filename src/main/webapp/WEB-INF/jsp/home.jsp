<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--@elvariable id="user" type="com.epam.entity.User"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.messages"/>
<html>
<head>
    <link rel="stylesheet" href="/static/css/main.css">
    <title>Home</title>
</head>
<body>
<%@include file="header.jspf" %>
<%--todo: use jstl in href--%>
<br/>
<%--<fmt:setLocale value="${locale}"/>--%>
<h2 style="color: #ff6ba1;">${insertApp}</h2>

<div class="row">
    hello world!
</div>

<c:if test="${not empty sessionScope.client}">Hello, ${client.login}! You are logged in! </c:if><br/>
</body>
</html>