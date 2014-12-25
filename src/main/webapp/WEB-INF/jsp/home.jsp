<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--@elvariable id="user" type="com.epam.entity.User"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.messages" />
<html>
<head>
    <title>Home</title>
</head>
<body>
<%--todo: use jstl in href--%>
Cars gallery
<br/>
<table border="1">
    <th>id</th>
    <th><fmt:message key="model"/></th>
    <th><fmt:message key="state"/></th>
    <th><fmt:message key="description"/></th>
    <th>image</th>
<c:forEach var="car" items="${cars}">
    <tr>
        <td>${car.id}</td>
        <td>${model}</td>
        <td>${car.state}</td>
        <td>${car.description}</td>
        <td><img src="/image/${car.id}" alt="${car.model}"/></td>
    </tr>
</c:forEach>
</table>
Hello,${user.email}! You are logged in! <br/>
<a href="${pageContext.request.contextPath}/do/logout">logout</a>
</body>
</html>