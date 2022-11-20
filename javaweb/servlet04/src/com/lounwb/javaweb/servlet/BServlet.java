package com.lounwb.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;

public class BServlet extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ServletContext application = this.getServletContext();
        Object obj1 = application.getAttribute("obj1");
        out.print("obj1: "+ obj1 +"<br>");
    }
}
