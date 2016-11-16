<%@ page import="com.tcp.study.model.LoginModel" %>
<%@ page import="com.tcp.study.VO.User" %>
<%@ page import="com.tcp.study.JsonParser" %><%--
  Created by IntelliJ IDEA.
  User: Sonkrat
  Date: 2016. 11. 3.
  Time: PM 4:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        div.main {
            display:block;
            width:500px;
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
                request.setCharacterEncoding("UTF-8");
                User reqUser = (User)request.getAttribute("reqUser");
                LoginModel loginModel = new LoginModel();
                reqUser.setName(loginModel.isUser(reqUser.getEmail(), reqUser.getPassword()));

                if (reqUser.getName() != "False")
                    out.println("<a target=\"_parent\" href=\"login.html\"><h1 class=\"text-center\">" + reqUser.getName() + "님 안녕하세요!" + "</h1></a><br/>");
                else
                    out.println("<a target=\"_parent\" href=\"login.html\"><h1 class=\"text-center\">" + "로그인 실패.." + "</h1></a><br/>");
            %>
        </div>
    </div>
</body>
</html>