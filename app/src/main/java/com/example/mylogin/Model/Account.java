package com.example.mylogin.Model;

import java.io.Serializable;


public class Account implements Serializable {
    private String userName;
    private String Password;

    public Account(String userName, String password) {
        this.userName = userName;
        Password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
