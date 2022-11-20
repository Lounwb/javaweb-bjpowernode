<%@ page import="com.lounwb.javaweb.javabean.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@page contentType="text/html;" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<c:forEach items="${userList}" var="u">
    username:${u.username} age:${u.age}<br>
</c:forEach>
