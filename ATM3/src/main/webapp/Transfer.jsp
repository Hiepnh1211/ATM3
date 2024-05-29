<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Models.User, Services.UserServices, Services.Utilities ,Models.Transaction"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transfer money</title>
</head>
<body>
		<%
			User user = (User)session.getAttribute("user");
			Utilities utils = new Utilities();
			UserServices userServices = new UserServices();
		%>
		User: <%= utils.padLeftZeros(user.getIdNumber(), 8) %> <br>
		Name: <%= user.getName() %> <br>
		Balance: <div id="output"></div> <br>
		
		<form action="Transfer" method="post">
			<table style="with: 100%">
    		<tr>
     		<td>Receiver</td>
     		<td><input type="number" name="receiver" maxlength="8"></td>
     		</tr>
     		<tr>
     		<td>Amount</td>
     		<td><input type="text" step="0.01" name="amount" value=0></td>
     		</tr>
     		</table>
     		<input type="submit" value="Transfer">
     		<input type="button" onclick="location.href='UserMenu.jsp';" value="Back">
		</form>
		
		<%
			String result = (String) session.getAttribute("transferResult");
        	if (result != null) {
            	out.println(result);
        	}
			
		%>
		
		<script>
    	var message = '<%= user.getBalance() %>';
    	document.getElementById('output').innerHTML = message;
		</script>
</body>
</html>