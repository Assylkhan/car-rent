<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/do/register" method="post">
  email: <input type="text" name="email"/>
  password: <input type="password" name="password"/>
  confirm password: <input type="password" name="confirmPass"/>
  <button type="submit">Signup</button>
  <div style="color: red">${confirmError}</div>
</form>
</body>
</html>
