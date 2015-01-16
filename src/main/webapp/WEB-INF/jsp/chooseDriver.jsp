<%--
  Created by IntelliJ IDEA.
  User: Асылхан
  Date: 16.01.2015
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Drivers</title>
</head>
<body>

<table>
    <th>id</th>
    <th>first name</th>
    <th>last name</th>
    <th>phone</th>
    <th>is free</th>
    <th>current location</th>
    <c:forEach items="${drivers}" var="driver">
        <tr onclick="document.location = '${pageContext.request.contextPath}/do/reportDriver'">
            <td>${driver.id}</td>
            <td>${driver.firt_name}</td>
            <td>${driver.last_name}</td>
            <td>${driver.phone}</td>
            <td>${driver.isFree}</td>
            <td>${driver.currentLocation}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
