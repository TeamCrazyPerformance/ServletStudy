package com.tcp.study.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tcp.study.model.BeerModel;

public class BeerSelect extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
//		res.setContentType("text/html");
//		PrintWriter out = res.getWriter();
//		out.println("Beer Selection Advice<br>");
//		String c = req.getParameter("color");
//		out.println("<br>Got beer color " + c);
		
		String c = req.getParameter("color");
		BeerModel model = new BeerModel();
		List result = model.getBrands(c);
		
//		res.setContentType("text/html");
//		PrintWriter out = res.getWriter();
//		out.println("beer selection advice<br>");
		
		
//		Iterator i = result.iterator();
//		
//		while(i.hasNext()) {
//			out.print("<br> try: " + i.next());
//		}
		
		req.setAttribute("styles", result);
		
		RequestDispatcher view = req.getRequestDispatcher("result.jsp");
		view.forward(req, res);
	}

}
