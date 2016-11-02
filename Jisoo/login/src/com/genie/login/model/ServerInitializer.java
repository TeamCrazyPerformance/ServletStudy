package com.genie.login.model;

import javax.servlet.http.HttpServlet;

public class ServerInitializer extends HttpServlet {

	public void init() {
		System.out.println("server startup");
	}
}
