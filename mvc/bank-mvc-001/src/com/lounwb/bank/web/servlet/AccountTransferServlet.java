package com.lounwb.bank.web.servlet;

import com.lounwb.bank.web.exceptions.AppException;
import com.lounwb.bank.web.exceptions.MoneyNotEnoughException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * @author Lounwb
 * @version 1.0
 * @since 1.0
 */
@WebServlet("/transfer")
public class AccountTransferServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String fromActno = request.getParameter("fromActno");
        String toActno = request.getParameter("toActno");
        Double money = (Double.parseDouble(request.getParameter("money")));
        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        ResultSet rs = null;
        int count = 0;
        try {
            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
            String url = "jdbc:mysql://localhost:3306/mvc";
            String user = "root";
            String password = "litianyu2002";
            conn = DriverManager.getConnection(url, user, password);
            String sql1 = "select balance from t_act where actno = ?";
            //获取数据库操作对象
            ps1 = conn.prepareStatement(sql1);
            //执行sql语句
            ps1.setString(1, fromActno);
            rs = ps1.executeQuery();
            //处理结果查询集
            if(rs.next()){
                double balance = rs.getDouble("balance");
                if(balance < money){
                    throw new MoneyNotEnoughException("对不起，您的余额不足");
                }
                //开启事务，关闭自动提交
                conn.setAutoCommit(false);
                String sql2 = "update t_act set balance = balance - ? where actno = ?";
                ps2 = conn.prepareStatement(sql2);
                ps2.setDouble(1,money);
                ps2.setString(2,fromActno);
                count += ps2.executeUpdate();

                String sql3 = "update t_act set balance = balance + ? where actno = ?";
                ps3 = conn.prepareStatement(sql3);
                ps3.setDouble(1,money);
                ps3.setString(2,toActno);
                count += ps3.executeUpdate();
                if(count!=2){
                    throw new AppException("app异常，请联系管理员");
                }
                conn.commit();
                out.print("<h1>转账成功！</h1>");
            }
        } catch (Exception e) {
            //遇到异常就回滚
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            //处理异常
            out.print(e.getMessage());
        } finally {
            //关闭资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps1 != null) {
                try {
                    ps1.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps2 != null) {
                try {
                    ps2.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps3 != null) {
                try {
                    ps3.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
