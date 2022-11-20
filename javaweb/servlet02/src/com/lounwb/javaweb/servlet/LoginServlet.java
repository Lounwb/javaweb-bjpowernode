package com.lounwb.javaweb.servlet;

import com.lounwb.javaweb.servlet.adapter.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

public class LoginServlet extends GenericServlet {
    @Override
    public void init(){
        System.out.println("servletConfig: "+this.getServletConfig());
    }
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse)
            throws ServletException, IOException {
        System.out.println("login's service method execute!");
    }
}
