package com.tcp.study.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sonkrat on 2016. 11. 2..
 */

public class LoginModel {
    List<String> userList = new ArrayList<>();
    public boolean isUser(String email, String password) {
        return email.equals("soye73@naver.com") && password.equals("yeol4901");
    }
}
