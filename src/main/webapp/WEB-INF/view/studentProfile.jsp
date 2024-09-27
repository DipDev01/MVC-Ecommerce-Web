<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>Welcome</p>
<form action="<%=request.getContextPath()%> /logout">
<button type="submit" name="submit" class="login-button">
Logout</button>
</form>
</body>
</html>