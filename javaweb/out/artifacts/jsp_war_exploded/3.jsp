<%@page contentType="text/html;charset=utf-8" %>
<%@page isELIgnored="false" %>

${pageContext.request.contextPath}
<br>
<%= request.getContextPath()%>
<br>
左结合：${empty null == true}
左结合：${(empty null) == true}
<hr>
右结合：${empty (null == true)}
<hr>
${param.username}${empty param.username ? "用户名不能为空":"欢迎访问"}
<%Object obj = new Object();%>
