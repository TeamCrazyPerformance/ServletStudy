package com.tcp.study.controller;

import com.tcp.study.User;
import com.tcp.study.model.LoginModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Sonkrat on 2016. 11. 2..
 */
public class SignupController extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        PrintWriter out = res.getWriter();

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        List<String> userList = (List<String>) getServletContext().getAttribute("userList");

        User user = new User(email, password, name);
        req.setAttribute("reqUser", user);
        req.setAttribute("userList", userList);

        RequestDispatcher view = req.getRequestDispatcher("signupResult.jsp");
        view.forward(req, res);
    }
}

