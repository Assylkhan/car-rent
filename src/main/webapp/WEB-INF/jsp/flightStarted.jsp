<%--
  Created by IntelliJ IDEA.
  User: Асылхан
  Date: 16.01.2015
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="${pageContext.request.contextPath}/do/flightStarted" method="get">
  <input type="hidden" value="started"/>
  <button type="submit">started</button>
</form>
</body>
</html>
