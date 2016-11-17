<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	boolean resultLogin = (boolean) request.getAttribute("resultLogin");
		if (resultLogin == true) {
			out.print("login Success");
		} else {
			out.print("login failed");
		}
		


%>


</body>
</html>