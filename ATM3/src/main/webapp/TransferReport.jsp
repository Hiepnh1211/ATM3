<%@page import="java.sql.Date"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Services.*, java.util.List, Models.User, Models.Transfer"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transfer report</title>
</head>
<%= Constants.TABLE_STYLE %>
<body>
	<div align="center">
	<% 
		Utilities utils = new Utilities();
		AdminServices adminServices = new AdminServices();
	%>
	
	Transfer report
	<%= Constants.DATE_PICKER %>
	<form action="TransferReport" method="post">
		<label for="datepicker">Select Date:</label>
		<input type="text" id="datepicker" name="date" value=<%= session.getAttribute("date") %>>
		<button type="submit">Submit</button>
		<input type="button" onclick="location.href='AdminMenu.jsp';" value="Back">
	</form>
	<br>
	<table>
		<tr>
			<th>Sender Name</th>
    		<th>Sender ID</th>
    		<th>Sender Balance</th>
    		<th>Transfer Amount</th>
    		<th>Receiver Name</th>
    		<th>Receiver ID</th>
    		<th>Receiver Balance</th>
		</tr>
		
	<%
		String result = (String)session.getAttribute("Empty");
		if(result == null){
			List<Transfer> transferRecord = (List<Transfer>)session.getAttribute("Transfer");
			if(transferRecord != null){
				for(Transfer transfer : transferRecord){
							
	%>
			<tr>
				<td><%= utils.getUserById(transfer.getIdNumber()).getName() %></td>
				<td><%= transfer.getIdNumber() %></td>
				<td><%= transfer.getBalance() %></td>
				<td><%= transfer.getAmount() %></td>
				<td><%= utils.getUserById(transfer.getReceiverID()).getName() %></td>
				<td><%= transfer.getReceiverID() %></td>
				<td><%= transfer.getReceiverBalance() %></td>
			</tr>
	<%
				}
			}
		}else{
			out.print(result);
		}
	%>
	</table>
	</div>

</body>
</html>