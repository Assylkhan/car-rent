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
    <link rel="stylesheet" href="/static/css/bootstrap.css">
    <link rel="stylesheet" href="/static/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/static/css/main.css">
    <title>Home</title>
</head>
<body>
<%@include file="header.jspf" %>
<%--todo: use jstl in href--%>
<br/>
<%--<fmt:setLocale value="${locale}"/>--%>
<h2 style="color: #ff6ba1;">${orderResponse}</h2>

<div class="row">
    <c:forEach var="car" items="${cars}">
        <div class="col-sm-3 col-md-2 ">
            <div class="thumbnail">
                <img style="height: 100px; width: 200px;" src="/static/image/nature.jpg" alt="${car.model}"/>

                <div align=center class="caption">
                    <span>${car.id}</span>
                    <p style="color: rgb(221, 13, 0)">${car.model}</p>
                    <p style="color: #204d74">${car.state}</p>
                    <p style="width: 100px; height: 43px; overflow: hidden;">${car.description}</p>
                    <p>
                    <form action="${pageContext.request.contextPath}/do/doOrder" method="GET">
                        <input type="hidden" value="${car.id}"/>
                        <input value="<fmt:message key="order"/>" type="submit" class="btn btn-danger">
                    </form>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<c:if test="${not empty sessionScope.client}">Hello, ${client.email}! You are logged in! </c:if><br/>
</body>
</html>