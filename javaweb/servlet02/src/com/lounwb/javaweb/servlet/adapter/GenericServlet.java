package com.lounwb.javaweb.servlet.adapter;

import jakarta.servlet.*;

import java.io.IOException;

public abstract class GenericServlet implements Servlet {

    private ServletConfig config;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.config = config;
        this.init();
    }
    public void init(){

    }
    @Override
    public ServletConfig getServletConfig() {
        return this.config;
    }

    @Override
    public abstract void service(ServletRequest servletRequest, ServletResponse servletResponse)
            throws ServletException, IOException;

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
