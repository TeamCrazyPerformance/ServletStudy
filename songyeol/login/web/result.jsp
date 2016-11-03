<%--
  Created by IntelliJ IDEA.
  User: Sonkrat
  Date: 2016. 11. 2.
  Time: PM 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Result</title>
</head>
<body>
<%
    List styles = (List)request.getAttribute("styles");
    Iterator i = styles.iterator();
    while(i.hasNext()) {
        out.print("<br>Your Choice: " + i.next());
    }
%>
</body>
</html>