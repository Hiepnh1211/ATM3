<%@page import="java.util.concurrent.Delayed"%>
<%@page import="Services.UserServices"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Models.User, Services.Utilities"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change Password</title>
</head>
<body>
	<%
		Utilities utils = new Utilities();
		User user = (User)session.getAttribute("user");
	%>
		
	Change password
	<form action="ChangePassword" method="post">
		<table style="with: 100%">
    		<tr>
     		<td>Old Password</td>
     		<td><input type="password" name="oldPass" maxlength="4"></td>
     		</tr>
     		<tr>
     		<td>New Password</td>
     		<td><input type="password" name="newPass" maxlength="4"></td>
     		</tr>
     		<tr>
     		<td>Confirm New Password</td>
     		<td><input type="password" name="confirmNewPass" maxlength="4"></td>
    		</tr>
   		</table>
   		<input type="submit" value="Change password" />
	</form>
	
	<% 
		out.print(utils.toMenu());
		String result = (String) session.getAttribute("passwordChangeResult");
    	if (result != null) {
        	out.println(result);
    	}
	%>
</body>
</html>