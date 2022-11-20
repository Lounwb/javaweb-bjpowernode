<%@ page import="com.lounwb.javaweb.javabean.User" %>
<%@page contentType="text/html;charset=utf-8"%>
<%@page isELIgnored="false" %>
<%
    session.setAttribute("name", "name");
    request.setAttribute("userObj", new User("jack","1234",10));
%>

${sessionScope}<br>
${userObj.username}
${pageContext.requtst.contextPath}