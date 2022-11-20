package com.lounwb.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class AServlet extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        ServletContext application = this.getServletContext();
        out.write("ServletContext对象是: "+ application + "<br>");
        Enumeration<String> initParameterNames = application.getInitParameterNames();
        while(initParameterNames.hasMoreElements()){
            String name = initParameterNames.nextElement();
            String value = application.getInitParameter(name);
            out.print(name + "=" + value + "<br>");
        }

        //获取应用上下文的根
        String contextPath = application.getContextPath();
        out.print(contextPath + "<br>");

        //获取文件的绝对路径
        String realPath = application.getRealPath("/common/common.html");
        out.print("common Path: " + realPath + "<br>");

       // application.log("你好 我是Lounwb");
        /*if( true){
            application.log("非法信息",new RuntimeException("未满18岁"));
        }*/
        User user = new User("zhangsan",1);
        application.setAttribute("obj1",user);
        Object obj1 = application.getAttribute("obj1");
        out.print(obj1 + "<br>");
    }
}
