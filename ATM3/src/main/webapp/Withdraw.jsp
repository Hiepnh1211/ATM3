<%@page import="Models.Transaction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Services.UserServices, Models.User, Services.Utilities, Services.Constants"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Withdraw</title>
</head>
<body>
<div align="center">

	<%
		Utilities utils = new Utilities();
		User user = (User)session.getAttribute("user");
		double balance = user.getBalance();
		UserServices userServices = new UserServices();
	%>

	Withdrawing Money <br>
	Balance: <div id="output"></div>
		<form action="Withdraw" method="post">
   			<table style="with: 100%">
    		<tr>
    		<td></td>
     		<td>Amount</td>
     		<td><input type="text" name="amount" value = 0></td>
    		</tr>
    		<tr>
   		</table>
   		<input type="submit" value="Withdraw" />
   		</form>
   		
   		<%
   			out.print(utils.toMenu());
   			String result = (String) session.getAttribute("withdrawResult");
    		if (result != null) {
    			out.println(result);
    		}
   		%>
   		<script>
    	var message = '<%= user.getBalance() %>';
    	document.getElementById('output').innerHTML = message;
		</script>
	</div>
</body>
</html>