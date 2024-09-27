<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Css/studentLogin.css" />
</head>
<body>
	<div class="login-box">
		<h2>Login</h2>
		<p style="color:red">${error}</p>
		<form action="<%=request.getContextPath()%>/Login" method="post">
			<div class="row">
				<div class="col">
					<label for="username">Username:</label> 
					<input type="text" id="username" name="username" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="password">Password:</label> 
					<input type="password" id="password" name="password" required>
				</div>
			</div>
			<button type="submit" name="submit" class="login-button">Login</button>
		</form>
		<a href="<%=request.getContextPath()%>/Register">Register</a>
	</div>
</body>
</html>