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
public class LoginController extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
//        PrintWriter out = res.getWriter();

        String email = req.getParameter("email");
        String password = req.getParameter("password");
//        List<String> tempList = (List<String>) getServletContext().getAttribute("tempList");

        User user = new User(email, password, "이송열");
        req.setAttribute("reqUser", user);

        RequestDispatcher view = req.getRequestDispatcher("loginResult.jsp");
        view.forward(req, res);
    }
}
