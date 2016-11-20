package com.tcp.study;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Sonkrat on 2016. 11. 20..
 */
public class ListenerTest extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        Dog dog = (Dog)getServletContext().getAttribute("dog");

        out.println(dog.getBreed());
    }
}
