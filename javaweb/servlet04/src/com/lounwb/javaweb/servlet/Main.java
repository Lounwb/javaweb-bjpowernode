package com.lounwb.javaweb.servlet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// 1:无需package
// 2: 类名必须Main, 不可修改

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int n1=0,n2=0;
        for (int i = 0; i < n; i++) {
            Integer score = scan.nextInt();
            if(score>=85){
                n2++;
                n1++;
            }else if(score>=60){
                n1++;
            }
        }
        System.out.println(Math.round(n1*100/n) + "%");
        System.out.println(Math.round(n2*100/n) + "%");
        //在此输入您的代码...
        scan.close();
    }
}