package com.tcp.study.VO;

/**
 * Created by Sonkrat on 2016. 11. 3..
 */
public class User {
    private String email;
    private String password;
    private String name;

    public User() {}

    public User(String email, String passwd, String name) {
        this.email = email;
        this.password = passwd;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwd) {
        this.password = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
