<%@page import="Models.Transaction"%>
<%@page import="Services.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Services.UserServices, Models.User, Services.Utilities, Services.Constants"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Deposit</title>
</head>
<body>
	<div align="center">
	
	<%
		Utilities utils = new Utilities();
		User user = (User)session.getAttribute("user");
		double balance = user.getBalance();
		UserServices userServices = new UserServices();
	%>
	
	Deposit Money <br>
	Balance: <div id="balance"></div>
		<form action="Deposit" method="post">
   			<table style="with: 100%">
    			<tr>
     			<td>Amount</td>
     			<td><input type="text" step="0.01" name="amount" value = 0></td>
    			</tr>
    			<tr>
   			</table>
   			<input type="submit" value="Deposit" />
   			<input type="button" onclick="location.href='UserMenu.jsp';" value="Back">
   		</form>
   		<%
   			String result = (String) session.getAttribute("depositResult");
        	if (result != null) {
        			out.println(result);
        			session.removeAttribute("depositResult");
        	}
   		%>
   		<script>
    	var message = '<%= user.getBalance() %>';
    	document.getElementById('balance').innerHTML = message;
		</script>
	</div>
</body>
</html>