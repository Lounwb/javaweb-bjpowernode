package com.lounwb.javaweb.servlet;

import jakarta.servlet.*;

import javax.management.StandardEmitterMBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class ConfigTestServlet extends GenericServlet {
/*    @Override
    public void init() throws ServletException {

    }*/
    @Override
    public void service(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ServletConfig config = getServletConfig();
        out.print("Config的对象是: " + config);
       // out.print("<br>");
        out.print("config对象的名字" + config.getServletName()+"<br>");
        Enumeration<String> initParameterNames = config.getInitParameterNames();
        while(initParameterNames.hasMoreElements()){
            String s = initParameterNames.nextElement();
            String parameter = config.getInitParameter(s);
            out.print(s+"  "+parameter+"<br>");
        }

    }
}
