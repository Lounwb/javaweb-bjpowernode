<%@ page import="com.lounwb.javaweb.javabean.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html;" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty param.username}">
    <h1>用户名不能为空</h1>
</c:if>
<c:if test="${not empty param.username}">
    <h1>欢迎你,${param.username}</h1>
</c:if>
<hr>
<%
    List<User> userList= new ArrayList<>();
    User u1 = new User("zhangsan",10);
    User u2 = new User("lisi",20);
    User u3 = new User("wangwu",30);
    userList.add(u1);
    userList.add(u2);
    userList.add(u3);
    request.setAttribute("userList", userList);
%>
<c:forEach  items="${userList}" var="u" varStatus="userStatus">
    编号:${userStatus.count} username:${u.username} age:${u.age}<br>
</c:forEach>
<%--
<c:forEach var="i" begin="1" end="10" step="1">
    ${i}<br>
</c:forEach>--%>
${pageContext.request.contextPath}
${requestScope.userList.get(0).username}
