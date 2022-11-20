package com.lounwb.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class BServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        Object obj = request.getAttribute("obj");
        PrintWriter out = response.getWriter();

        out.print(obj);
        /*产品创意-->产品用户体验-->
                写产品设计说明书*/
    }
}
