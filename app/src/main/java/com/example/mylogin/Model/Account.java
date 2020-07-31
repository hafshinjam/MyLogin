package com.example.mylogin.Model;

import java.io.Serializable;
import java.util.Objects;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return userName.equals(account.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }
}
