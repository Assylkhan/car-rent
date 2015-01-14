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
<div class="container-fluid">
    <form action="${pageContext.request.contextPath}/do/doOrder" method="POST">
        <div class="form-group">
            <label class="control-label"><fmt:message key="pickupDate"/></label>
            <input type="date" placeholder="dd-mm-yyyy" name="pickupDate"/>
        </div>
        <div class="form-group">
            <label class="control-label"><fmt:message key="returnDate"/></label>
            <input type="date" name="returnDate"/>
        </div>
        <div class="form-group">
            <label class="control-label"><fmt:message key="surname"/></label>
            <input type="text" name="surname"/>
        </div>
        <div class="form-group">
            <label class="control-label"><fmt:message key="name"/></label>
            <input type="text" name="name"/>
        </div>
        <div class="form-group">
            <label class="control-label"><fmt:message key="citizenship"/></label>
            <input type="text" name="citizenship"/>
        </div>
        <div class="form-group">
            <label class="control-label"><fmt:message key="gender"/></label>
            <input type="text" name="gender"/>
        </div>
        <div class="form-group">
            <label class="control-label"><fmt:message key="birthDate"/></label>
            <input type="date" name="birthDate"/>
        </div>
        <div class="form-group">
            <label class="control-label"><fmt:message key="birthPlace"/></label>
            <input type="text" name="birthPlace"/>
        </div>
        <div class="form-group">
            <label class="form-group"><fmt:message key="issuedAgency"/></label>
            <input type="text" name="issuedAgencyName"/>
        </div>
        <div class="form-group">
            <label class="control-label"><fmt:message key="issuedDate"/></label>
            <input type="date" name="issuedDate"/>
        </div>
        <div class="form-group">
            <label class="control-label"><fmt:message key="validity"/></label>
            <input type="date" name="validity"/>
        </div>
        <button class="btn btn-danger" type="submit"><fmt:message key="order"/></button>
    </form>
</div>
</body>
</html>
