<%--
  Created by IntelliJ IDEA.
  User: Асылхан
  Date: 05.01.2015
  Time: 5:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Applications</title>
</head>
<body>
<header class="navbar navbar-brand">
    <ul class="list-unstyled pull-right" style="margin: 15px;">
        <li><strong><a href="${pageContext.request.contextPath}/do/logout">logout</a></strong></li>
    </ul>
</header>
<div class="container">

    <table>
        <th>id</th>
        <th>client name</th>
        <th>start location</th>
        <th>end location</th>
        <th>car type</th>
        <c:forEach items="${applications}" var="app">
            <tr>
                <td>${app.id}</td>
                <td>${app.client.name}</td>
                <td>${app.startPlace}</td>
                <td>${app.endPlace}</td>
                <td>${app.destination}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/do/chooseDriver">
                        <input type="hidden" value="${app}" name="app">
                        <button type="submit">report to driver</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>