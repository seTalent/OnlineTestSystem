package com.lovejava.pojo;

public class Admin {
    private Integer aid;

    private String userName;

    private String password;

    public Admin(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    @Override
    public String toString() {
        return "Admin{" +
                "aid=" + aid +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}