<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		int result = (int) request.getAttribute("signup");
		if (result == 0) {
			out.print("SignUp Success");
		} else if (result == 1) {
			out.print("SignUp Fail : Blank ID");
		} else if (result == 2) {
			out.print("SignUp Fail : Overlap ID");
		} else if (result == 3) {
			out.print("SignUp Fail : Blank Name");
		} else if (result == 4) {
			out.print("SignUp Fail : Blank Password");
		}
	%>

</body>
</html>