package com.tcp.study.controller;

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
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("Beer Selection Advice<br>");
        String c = req.getParameter("color");
        List<String> beerList = new LoginModel().getMembers(c);

        req.setAttribute("styles", beerList);
        RequestDispatcher view = req.getRequestDispatcher("result.jsp");
        view.forward(req, res);
    }
}
