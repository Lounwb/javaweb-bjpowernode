<%@page contentType="text/html;charset=UTF-8" %>
<%@page session="false" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>欢迎使用OA系统</title>
	</head>
	<body>
		<%--<a href="<%=request.getContextPath()%>/dept/list">查看部门列表</a>--%>
		<h1 align="center">登录</h1>
		<form action="<%=request.getContextPath()%>/user/login" method="post" align="center">
			username: <input type="text" name="username"><br>
			password: <input type="password" name="password"><br>
			<input type="checkbox" name="f" value="1">十天内免登录<br>
			<input type="submit" value="登录">
		</form>
	</body>
</html>
