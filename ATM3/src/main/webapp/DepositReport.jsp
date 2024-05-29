<%@page import="java.sql.Date"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Services.*, java.util.List, Models.Transaction, Models.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Deposit report</title>
</head>
 <%= Constants.TABLE_STYLE %>
<body>
<div align="center">

	<% 
		Utilities utils = new Utilities();
		AdminServices adminServices = new AdminServices();
		User admin = (User)session.getAttribute("admin");
	%>

	Deposit report
	<% out.print(Constants.DATE_PICKER); %>
	<form action="DepositReport" method="post">
		<label for="datepicker">Select Date:</label>
		<input type="text" id="datepicker" name="date" value=<%= session.getAttribute("date")%>>
		<button type="submit">Submit</button>
		<input type="button" onclick="location.href='AdminMenu.jsp';" value="Back">
	</form>
	<br>
	<table>
			<tr>
				<th>Name</th>
    			<th>User ID</th>
    			<th>Withdraw Amount</th>
    			<th>Balance</th>
			</tr>
	
	<%
	if(admin != null){
		String result = (String)session.getAttribute("Empty");
		if(result == null){
			List<Transaction> depositRecord = (List<Transaction>)session.getAttribute("Deposit");
			if(depositRecord != null){
				for(Transaction transaction : depositRecord){
							
	%>
		
			<tr>
				<td><%= utils.getUserById(transaction.getIdNumber()).getName() %></td>
				<td><%= transaction.getIdNumber() %></td>
				<td><%= transaction.getAmount() %></td>
				<td><%= transaction.getBalance() %></td>
			</tr>
	
	<%
				}
			}
		}else{
			out.print(result);
		}
	}
	%>
	</table>
</div>
</body>
</html>