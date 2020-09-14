package com.grapefruit.token.entity;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 * @ModifyTime 2020/9/9 19:52:20
 */
public class Stu {

    private String userName;

    private String password;

    public Stu(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Stu() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Stu{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
