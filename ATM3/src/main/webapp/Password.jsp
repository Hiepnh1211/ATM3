<%@page import="java.lang.ref.Cleaner"%>
<%@page import="jakarta.websocket.OnClose"%>
<%@page import="Services.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Models.User, Services.UserServices, Services.Utilities"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Password Check</title>
</head>
<body>
	<div align="center">
		Welcome
		<%
			Utilities utils = new Utilities();
			User user = (User)session.getAttribute("user");
			out.print(user.getRole() + " " + user.getName());
		%>
		
		  <form action="PasswordCheck" method="post">
   			<table style="with: 100%">
    		<tr>
     		<td>Password</td>
     		<td><input type="password" maxlength="4" name="password" /></td>
    		</tr>
    		<tr>
   		</table>
   		<input type="submit" value="Log in"/>
   		<input type="button" onclick="location.href='IdCheck.jsp';" value="Back">
  </form>
  		<%
            String result = (String) session.getAttribute("loginResult");
            if (result != null) {
                out.println(result);
            }
        %>
	</div>
</body>
</html>