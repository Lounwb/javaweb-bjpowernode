package com.lounwb.javaweb.servlet;

import jakarta.servlet.Servlet;
import jakarta.servlet.annotation.WebServlet;

import static java.lang.Class.forName;

public class ReflectAnnotation {
    public static void main(String[] args) {
        Class<?> aClass = null;
        try {
            aClass =  forName("com.lounwb.javaweb.servlet.HelloServlet");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        boolean annotationPresent = aClass.isAnnotationPresent(WebServlet.class);
        if (annotationPresent) {
            WebServlet annotation = aClass.getAnnotation(WebServlet.class);
            String[] value = annotation.value();
            for(String s : value){
                System.out.println(s);
            }
        }
    }
}
