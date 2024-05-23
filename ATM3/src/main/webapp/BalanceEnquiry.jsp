<%@page import="Services.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Models.User, Services.UserServices, Services.Utilities ,Models.Transaction, java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Balance Enquiry</title>
</head>
<body>
	<div>
		<%
			User user = (User)session.getAttribute("user");
			Utilities utils = new Utilities();
			List<Transaction> transactionRecord = utils.getTransactionRecord(user.getIdNumber());
		%>
		User: <%= utils.padLeftZeros(user.getIdNumber(), Constants.ID_NUMBER_LENGTH) %> <br>
		Name: <%= user.getName() %> <br>
		Balance: <%= user.getBalance() %> <br>
		Balance Enquiry: <br>
		<%
			out.print(Constants.TABLE_STYLE);
		%>
		<table>
			<tr>
				<th>Transaction Type</th>
    			<th>Date</th>
    			<th>Amount</th>
    			<th>Balance</th>
			</tr>
			<%
				for(int i = 0; i < transactionRecord.size(); i++){
						
			%>
				<tr>
					<td><%=transactionRecord.get(i).getType() %></td>
					<td><%=transactionRecord.get(i).getDate().toString() %></td>
					<td><%=transactionRecord.get(i).getAmount()%></td>
					<td><%=transactionRecord.get(i).getBalance() %></td>
				</tr>
			
			<%
					if(i == 4){
						break;
					}
				}
			%>
		</table>		
	</div>
	<input type="button" onclick="location.href='UserMenu.jsp';" value="Back">
</body>
</html>