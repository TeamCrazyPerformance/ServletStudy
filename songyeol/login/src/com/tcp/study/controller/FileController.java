package com.tcp.study.controller;

import com.tcp.study.JsonParser;
import com.tcp.study.VO.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.*;
import java.util.List;

/**
 * Created by Sonkrat on 2016. 11. 17..
 */
public class FileController extends HttpServlet {

    public static List<User> userList;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            // 상대경로로 수정해야함.
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/Sonkrat/Git/ServletStudy/songyeol/login/src/com/tcp/study/database/user.txt"),"UTF8"));
            userList = JsonParser.getInstance().getParsed(bufferedReader);
            return;
        }
        catch (FileNotFoundException ignored) { }   // no saved state
        catch (IOException ignored) { }             // problem during read
        catch (NumberFormatException ignored) { }   // corrupt saved state
    }
}
