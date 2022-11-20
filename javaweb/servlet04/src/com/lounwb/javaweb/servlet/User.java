package com.lounwb.javaweb.servlet;

public class User {
    private String name;
    private int no;

    public User() {
    }

    public User(String name, int no) {
        this.name = name;
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", no=" + no +
                '}';
    }
}
