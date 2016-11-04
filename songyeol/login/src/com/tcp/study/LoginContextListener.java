package com.tcp.study;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sonkrat on 2016. 11. 3..
 */
public class LoginContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        ServletContext sc = event.getServletContext();
        List<User> userList = new ArrayList();
        List<String> tempList = new ArrayList();
        String userFile = sc.getInitParameter("user.txt");
        InputStream is = null;
        BufferedReader reader = null;
        try {
            is = sc.getResourceAsStream(userFile);
            reader = new BufferedReader(new InputStreamReader(is));
            String record;

            while ((record=reader.readLine()) != null) {
                if (!(record == "[" || record == "]")) {
                    tempList.add(record);
                }
            }
            sc.setAttribute("tempList", tempList);
            sc.setAttribute("userList", userList);
            sc.log("User data has benn loaded.");
        } catch (Exception e) {
            sc.log("Exception occured while processing the user file.", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                }
            }
        }
    }

    public void contextDestroyed(ServletContextEvent event) {

    }
}
