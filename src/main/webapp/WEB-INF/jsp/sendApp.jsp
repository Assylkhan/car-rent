<%--
  Created by IntelliJ IDEA.
  User: Асылхан
  Date: 05.01.2015
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n.messages"/>
<html>
<head>
    <link rel="stylesheet" href="/static/css/bootstrap.css">
    <link rel="stylesheet" href="/static/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/static/css/main.css">
    <%--<script src="/static/js/jquery-2.1.3.js"/>--%>
    <title>Order</title>
</head>
<body>
<%@include file="header.jspf"%>
<div class="container-fluid">
    <p><strong style="color: darkred">${insertApp}</strong></p>
    <form action="${pageContext.request.contextPath}/do/sendApp" method="POST">
        <div class="form-group">
            <label class="form-group">destination</label>
            <select name="destination">
                <option value="VIP">vip</option>
                <option value="USUALLY">usually</option>
                <option value="ECONOM">econom</option>
            </select>
        </div>
        <div class="form-group">
            <label class="control-label">start location</label>
            <input type="date" name="startPlace"/>
        </div>
        <div class="form-group">
            <label class="control-label">end location</label>
            <input type="date" name="endPlace"/>
        </div>
        <button class="btn btn-danger" type="submit">send application</button>
    </form>
</div>
</body>
</html>
