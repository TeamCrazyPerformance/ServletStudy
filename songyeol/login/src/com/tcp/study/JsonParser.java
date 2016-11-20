package com.tcp.study;

import com.tcp.study.VO.User;

import java.io.*;
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

    public List<User> getParsed(BufferedReader bufferedReader) {    // 서버 시작할 때 한번만 구동
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
            bufferedReader.close();
        } catch (IOException ignored) {}

        return null;
    }

    public void toParsing(User user) {
        String origin;
        String parsing = "{" + "\"email\"" + ":" + "\"" + user.getEmail() + "\"" + ","
                             + "\"password\"" + ":" + "\"" + user.getPassword() + "\"" + ","
                             + "\"name\"" + ":" + "\"" + user.getName() + "\"" + "}"
                             + "\n" + "]";
        String filePath = "/Users/Sonkrat/Git/ServletStudy/songyeol/login/src/com/tcp/study/database/user.txt";
        File inputFile = new File(filePath);
        File outputFile = new File(filePath + ".temp");
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        boolean result = false; // 성공 여부 판별


        try {
            // 상대경로로 수정해야함
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile),"UTF-8"));

            // "]" 이전 데이터 before에 저장.
            String before ="";
            if (userList.size() != 0) {
                for (int i = 0; i < userList.size(); i++) {
                    origin = bufferedReader.readLine();
                    before += (origin + "\r\n");
                }
                origin = bufferedReader.readLine();
                before += (origin + ",\r\n");
            }
            else {
                before += ("[" + "\r\n");
            }
            bufferedReader.close();

            // BufferedWriter를 이용해서 덮어쓰기.
            // 상대경로로 수정해야함
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile),"UTF-8"));
            bufferedWriter.write(before + parsing);
            bufferedWriter.close();
            userList.add(user);
            result = true;
        } catch (IOException ignored) {}
        finally {
            // 리소스 해제 및 파일 교체
            try {
                bufferedReader.close();
            } catch (IOException ignored) { }
            try {
                bufferedWriter.close();
            } catch (IOException ignored) { }

            if (result) {
                inputFile.delete();
                outputFile.renameTo(new File(filePath));
            }
        }
    }

}
