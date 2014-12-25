<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div>
  <form action="${pageContext.request.contextPath}/do/login" method="post">
    Email: <input type="text" name="email"/>
    Password: <input type="password" name="password"/>
    <button type="submit">Login</button>
    <div style="color:red">${loginError}</div>
  </form>
</div>
</body>
</html>
