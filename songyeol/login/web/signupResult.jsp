<%@ page import="com.tcp.study.VO.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tcp.study.JsonParser" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %> <%--
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
            String message ="";
            Map<String, String> log = new HashMap();

            if (reqUser.getEmail().equals("")) {
                message += "아이디미입력/";
                count += 1;
            }
            if (reqUser.getPassword().equals("")) {
                message += "비밀번호미입력/";
                count += 2;
            }
            if (reqUser.getName().equals("")) {
                message += "이름미입력/";
                count += 4;
            }

            for (User user : userList) {
                if (reqUser.getEmail().equals(user.getEmail())) {
                    message += "이메일중복/";
                    count += 8;
                    break;
                }
            }

            if (count == 0) {
                JsonParser.getInstance().toParsing(reqUser);
                out.println("<a target=\"_parent\" href=\"login.html\"><h1 class=\"text-center\">" + reqUser.getName() + "님 반갑습니다!" + "</h1></a><br/>");
            }
            else {
                log.put("error", "signup");
                log.put("desc", "회원가입 실패");
                log.put("message", message);

                if (count >= 8) {
                    log.put("time", new Date().toString());
                    JsonParser.getInstance().toParsingLog(log);

                    out.println("<a target=\"_parent\" href=\"signup.html\"><h1 class=\"text-center\">" + "이미 사용중이거나 탈퇴한 계정입니다." + "</h1></a><br/>");
                }
                else {
                    log.put("time", new Date().toString());
                    JsonParser.getInstance().toParsingLog(log);

                    out.println("<a target=\"_parent\" href=\"signup.html\"><h1 class=\"text-center\">" + "입력하신 정보를 다시 확인해주세요." + "</h1></a><br/>");
                }
            }


        %>
    </div>
</div>
</body>
</html>