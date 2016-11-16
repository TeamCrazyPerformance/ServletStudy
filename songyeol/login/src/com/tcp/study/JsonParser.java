package com.tcp.study;

import com.tcp.study.VO.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sonkrat on 2016. 11. 9..
 */
public class JsonParser {
    private static JsonParser instance = null;
    private List<User> userList;

    public static JsonParser getInstance() {
        if (instance == null)
            instance = new JsonParser();
        return instance;
    }

    public List<User> getUserList() {
        return userList;
    }

    public List<User> getParsed(BufferedReader bufferedReader) {
        try {
            String origin;
            userList = new ArrayList();

            while ((origin = bufferedReader.readLine()) != null) {
                if (!(origin.equals("[") || origin.equals("]"))) {
                    User user = new User();
                    String[] array1 = origin.split("\\{");
                    String[] array2 = array1[1].split("}");
                    String[] array3 = array2[0].split(",");

                    for (String info : array3) {
                        String[] map = info.split(":");
                        switch (map[0]) {
                            case "\"email\"":
                                user.setEmail(map[1].split("\"")[1]);
                                break;
                            case "\"password\"":
                                user.setPassword(map[1].split("\"")[1]);
                                break;
                            case "\"name\"":
                                user.setName(map[1].split("\"")[1]);
                                break;
                        }
                    }
                    userList.add(user);
                }
            }
        } catch (IOException ignored) {}

        return null;
    }
}
