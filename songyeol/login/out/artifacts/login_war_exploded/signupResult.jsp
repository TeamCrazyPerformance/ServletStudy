<%@ page import="com.tcp.study.VO.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tcp.study.JsonParser" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.io.BufferedWriter" %>
<%@ page import="java.io.FileWriter" %><%--
  Created by IntelliJ IDEA.
  User: Sonkrat
  Date: 2016. 11. 3.
  Time: PM 4:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        div.main {
            display:block;
            width:600px;
            padding-top: 100px;
            margin:0 auto;
            position: relative;
        }
    </style>
    <title>login</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div>
    <div class="main">
        <%
            int count = 0;
            User reqUser = (User)request.getAttribute("reqUser");
            List<User> userList = JsonParser.getInstance().getUserList();

            for (User user : userList) {
                if (reqUser.getEmail().equals("") || reqUser.getPassword().equals("") || reqUser.getName().equals("")) {
                    out.println("<a target=\"_parent\" href=\"signup.html\"><h1 class=\"text-center\">" + "입력하신 정보를 다시 확인해주세요." + "</h1></a><br/>");
                    count++;
                    break;
                }

                if (reqUser.getEmail().equals(user.getEmail())) {
                    out.println("<a target=\"_parent\" href=\"signup.html\"><h1 class=\"text-center\">" + "이미 사용중이거나 탈퇴한 아이디입니다." + "</h1></a><br/>");
                    count++;
                    break;
                }
            }

            if (userList.size() == 0) {
                if (reqUser.getEmail().equals("") || reqUser.getPassword().equals("") || reqUser.getName().equals("")) {
                    out.println("<a target=\"_parent\" href=\"signup.html\"><h1 class=\"text-center\">" + "입력하신 정보를 다시 확인해주세요." + "</h1></a><br/>");
                    count++;
                }
            }

            if (count == 0) {
                JsonParser.getInstance().toParsing(reqUser);
                out.println("<a target=\"_parent\" href=\"signup.html\"><h1 class=\"text-center\">" + reqUser.getName() + "님 반갑습니다!" + "</h1></a><br/>");
            }
        %>
    </div>
</div>
</body>
</html>