package com.tcp.study.controller;

import com.tcp.study.JsonParser;
import com.tcp.study.VO.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by Sonkrat on 2016. 11. 2..
 */
public class LoginController extends HttpServlet {
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

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
//        PrintWriter out = res.getWriter();

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new User(email, password, "reqUser");
        req.setAttribute("reqUser", user);

        RequestDispatcher view = req.getRequestDispatcher("loginResult.jsp");
        view.forward(req, res);
    }
}
