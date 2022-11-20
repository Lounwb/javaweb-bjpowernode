package com.lounwb.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


public class StudentServlet implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {
        //设置相应内容类型
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取数据库连接对象
            String url = "jdbc:mysql://localhost:3306/lounwb";
            String user = "root";
            String password = "litianyu2002";
            conn = DriverManager.getConnection(url,user,password);
            //获取预编译的数据库操作对象
            String sql = "select no,name from t_student";
            ps = conn.prepareStatement(sql);
            //执行sql
            rs = ps.executeQuery();
            //处理查询结果集
            while(rs.next()){
                String no = rs.getString("no");
                String name = rs.getString("name");
                out.print(no + "," + name + "<br>");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {

                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
