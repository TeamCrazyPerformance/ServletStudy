package com.tcp.study.controller;

import com.tcp.study.JsonParser;
import com.tcp.study.VO.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
            FileReader fileReader = new FileReader("/Users/Sonkrat/ServletStudy/songyeol/login/src/com/tcp/study/database/user.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            userList = JsonParser.getInstance().getParsed(bufferedReader);
            return;
        }
        catch (FileNotFoundException ignored) { }   // no saved state
        catch (IOException ignored) { }             // problem during read
        catch (NumberFormatException ignored) { }   // corrupt saved state
    }
}
