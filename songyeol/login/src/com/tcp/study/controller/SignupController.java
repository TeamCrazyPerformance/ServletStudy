package com.tcp.study.controller;

import com.tcp.study.JsonParser;
import com.tcp.study.VO.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Sonkrat on 2016. 11. 2..
 */
public class SignupController extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        List<User> userList = JsonParser.getInstance().getUserList();

        User user = new User(email, password, name);
        req.setAttribute("reqUser", user);
        req.setAttribute("userList", userList);

        RequestDispatcher view = req.getRequestDispatcher("signupResult.jsp");
        view.forward(req, res);

    }
}

