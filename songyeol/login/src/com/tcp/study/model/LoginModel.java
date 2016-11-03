package com.tcp.study.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sonkrat on 2016. 11. 2..
 */

public class LoginModel {
    List<String> memberList = new ArrayList<>();
    public List<String> getMembers(String color) {
        if (color.equals("amber")) {
            memberList.add("Jack amber");
            memberList.add("Red moose");
        } else {
            memberList.add("Jai pail Ale");
            memberList.add("Gout shout");
        }
        return memberList;
    }
}
