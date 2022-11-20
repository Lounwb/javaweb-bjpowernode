<%@ page import="java.util.List" %>
<%@ page import="com.lounwb.oa.bean.Dept" %>
<%@page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>部门列表页面</title>
	</head>
	<body>
		<script type="text/javascript">
			function del(deptno){
				if(window.confirm("确认要删除吗?")){
					document.location.href = "/oa/dept/delete?deptno=" + deptno;
				}
			}
		</script>
		<h3>欢迎<%=session.getAttribute("username")%></h3><br>
        <a href="<%=request.getContextPath()%>/user/exit">[退出登录]</a>
		<h1 align="center">部门列表</h1>
		<hr >
		<table border="1px" align="center" width="50%">
			<tr>
				<th>序号</th>
				<th>部门编号</th>
				<th>部门名称</th>
				<th>操作</th>
			</tr>

			<%
				List<Dept> deptList = (List<Dept>) request.getAttribute("deptList");
				int i = 0;
				for(Dept dept:deptList){

			%>
			<tr>
				<th><%=++i%></th>
				<th><%=dept.getDeptno()%></th>
				<th><%=dept.getDname()%></th>
				<th>
					<a href="javascript:void(0)" onclick="del(<%=dept.getDeptno()%>)">删除</a>
					<a href="<%=request.getContextPath()%>/dept/detail?f=edit&deptno=<%=dept.getDeptno()%>">修改</a>
					<a href="<%=request.getContextPath()%>/dept/detail?f=detail&deptno=<%=dept.getDeptno()%>">详情</a>
				</th>
			</tr>
			<%
				}
			%>
		</table>
		<hr >
		<a href="<%=request.getContextPath()%>/old/add.jsp.jsp" >新增部门</a>
	</body>
</html>
