package com.tcp.study.model;

import com.tcp.study.JsonParser;
import com.tcp.study.VO.User;

/**
 * Created by Sonkrat on 2016. 11. 2..
 */

public class LoginModel {

    public String isUser(String email, String password) {
        String state = "False";
        for (User user : JsonParser.getInstance().getUserList())
            if (email.equals(user.getEmail()) && password.equals(user.getPassword()))
                return user.getName();
        return state;
    }
}
