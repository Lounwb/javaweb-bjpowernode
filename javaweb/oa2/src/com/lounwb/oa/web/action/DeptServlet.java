package com.lounwb.oa.web.action;

import com.lounwb.oa.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet({"/dept/list", "/dept/save", "/dept/modify", "/dept/edit", "/dept/delete", "/dept/detail"})
public class DeptServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if(servletPath.equals("/dept/list")){
            doList(request, response);
        }else if(servletPath.equals("/dept/save")){
            doSave(request, response);
        }else if(servletPath.equals("/dept/modify")){
            doModify(request, response);
        }else if(servletPath.equals("/dept/edit")){
            doEdit(request, response);
        }else if(servletPath.equals("/dept/delete")){
            doDelete(request, response);
        }else if(servletPath.equals("/dept/detail")){
            doDetail(request, response);
        }
    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String deptno = request.getParameter("deptno");

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("	<head>");
        out.print("		<meta charset='utf-8'>");
        out.print("		<title>详情页面</title>");
        out.print("	</head>");
        out.print("	<body>");
        out.print("		<h1>部门详情</h1>");
        out.print("		<hr >");
        out.print("<table border='1px' align='center' width='50%'>");
        out.print("			<tr>");
        out.print("				<th>部门编号</th>");
        out.print("				<th>部门名称</th>");
        out.print("				<th>部门位置</th>");
        out.print("			</tr>");

        try {
            conn = DBUtil.getConnection();
            String sql = "select * from dept where deptno=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,deptno);
            rs = ps.executeQuery();
            while(rs.next()){
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                out.print("			<tr>");
                out.print("				<th>"+deptno+"</th>");
                out.print("				<th>"+dname+"</th>");
                out.print("				<th>"+loc+"</th>");
                out.print("			</tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }

        out.print("		</table>");
        out.print("		<input type='button' value='后退' onclick='window.history.back()'/>");
        out.print("	</body>");
        out.print("</html>");
    }

    private void doEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String contextPath = request.getContextPath();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("        <!DOCTYPE html>");
        out.print("<html>");
        out.print("	<head>");
        out.print("		<meta charset='utf-8'>");
        out.print("		<title>修改部门</title>");
        out.print("	</head>");
        out.print("	<body>");
        out.print("		<h1>修改部门</h1>");
        out.print("		<hr >");
        out.print("		<form action='"+contextPath+"/dept/modify' method='post'>");

        //从前端获取部门编号
        String deptno = request.getParameter("deptno");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select dname,loc from dept where deptno=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,deptno);
            rs = ps.executeQuery();
            if(rs.next()){
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                out.print("部门编号:<input type='text' name='deptno' value='"+deptno+"' readonly/><br>");
                out.print("部门名称:<input type='text' name='dname' value='"+dname+"'/><br>");
                out.print("部门位置:<input type='text' name='loc' value='"+loc+"' /><br>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }

        out.print("			<input type='submit' value='修改'/>");
        out.print("		</form>");
        out.print("	</body>");
        out.print("</html>");
    }

    private void doModify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String contextPath = request.getContextPath();
        request.setCharacterEncoding("UTF-8");
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");
        int count = 0;
        //连接数据库执行update语句
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "update dept set dname = ?,loc = ? where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,dname);
            ps.setString(2,loc);
            ps.setString(3,deptno);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,null);
        }

        if (count != 0) {
            response.sendRedirect(contextPath + "/dept/list");
        }else {
            response.sendRedirect(contextPath +"/error.html");
        }
    }

    private void doSave(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String contextPath = request.getContextPath();
        //获取部门信息
        request.setCharacterEncoding("utf-8");
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");
        //连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            //执行sql语句插入到数据库中
            conn = DBUtil.getConnection();
            String sql = "insert into dept(deptno,dname,loc) values(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,deptno);
            ps.setString(2,dname);
            ps.setString(3,loc);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,null);
        }
        if (count != 0) {
            response.sendRedirect(contextPath + "/dept/list");
        }else {
            response.sendRedirect(contextPath +"/error.html");
        }
    }

    private void doList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String contextPath = request.getContextPath();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("	<head>");
        out.print("		<meta charset='utf-8'>");
        out.print("		<title>部门列表页面</title>");
        out.print("	</head>");
        out.print("	<body>");
        out.print("        <script type='text/javascript'>");
        out.print("                function del(deptno){");
        out.print("            if(window.confirm('确认要删除吗?')){");
        out.print("                document.location.href = '"+contextPath+"/dept/delete?deptno=' + deptno;");
        out.print("            }");
        out.print("        }");
        out.print("		</script>");
        out.print("		<h1 align='center'>部门列表</h1>");
        out.print("		<hr >");
        out.print("		<table border='1px' align='center' width='50%'>");
        out.print("			<tr>");
        out.print("				<th>序号</th>");
        out.print("				<th>部门编号</th>");
        out.print("				<th>部门名称</th>");
        out.print("				<th>操作</th>");
        out.print("			</tr>");

        try {
            conn = DBUtil.getConnection();
            String sql = "select deptno,dname,loc from dept";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            int i=0;
            while(rs.next()){
                String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                out.print("			<tr>");
                out.print("				<th>"+(++i)+"</th>");
                out.print("				<th>"+deptno+"</th>");
                out.print("				<th>"+dname+"</th>");
                out.print("				<th>");
                out.print("					<a href='javascript:void(0)' onclick='del("+deptno+")'>删除</a>");
                out.print("					<a href='"+contextPath+"/dept/edit?deptno="+deptno+"'>修改</a>");
                out.print("					<a href='"+contextPath+"/dept/detail?deptno="+deptno+"'>详情</a>");
                out.print("				</th>");
                out.print("			</tr>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }


        out.print("		</table>");
        out.print("		<hr >");
        out.print("		<a href='"+contextPath+"/add.html' >新增部门</a>");
        out.print("	</body>");
        out.print("</html>");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String deptno = request.getParameter("deptno");

        Connection conn = null;
        PreparedStatement ps = null;

        int count = 0;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            String sql = "delete from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,deptno);
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,null);
        }
        if (count==0) {
            response.sendRedirect(request.getContextPath()+ "/dept/list");
        }else {
            response.sendRedirect(request.getContextPath()+ "/error.html");
        }
    }
}
