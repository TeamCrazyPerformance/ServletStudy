package com.tcp.study;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Sonkrat on 2016. 11. 20..
 */
public class MyServletContextListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext sc = event.getServletContext();
        String dogBreed = sc.getInitParameter("breed");
        Dog d = new Dog(dogBreed);
        sc.setAttribute("dog", d);
        System.out.println("dog:" + d.toString());
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }
}
