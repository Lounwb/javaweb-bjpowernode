package com.lounwb.javaweb.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
public class Filter1 implements Filter {

    public Filter1() {
        System.out.println("无参数构造方法执行");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init方法执行");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("doFilter方法开始执行");
        chain.doFilter(request, response);
        System.out.println("doFilter方法执行结束");
    }

    @Override
    public void destroy() {
        System.out.println("destroy方法结束");
    }
}
