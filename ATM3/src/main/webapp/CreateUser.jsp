<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Services.Utilities, Models.User, Services.AdminServices, Services.Constants"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create user</title>
</head>
<body>
	<div align="left">
	<% 
		Utilities utils = new Utilities();
		AdminServices adminServices = new AdminServices();
		User admin = (User)session.getAttribute("admin");
	%>
		Create user
		<form action="CreateUser" method="post">
			<table>
				<tr>
					<td>Name:</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>Gender</td>
					<td><select name="gender">
							<option>Male</option>
							<option>Female</option>
					</select></td>
				</tr>
				<tr>
					<td>Contact number</td>
					<td><input type="text" maxlength="10" name="contactNumber"></td>
				</tr>
				<tr>
					<td>Address:</td>
					<td><input type="text" name="address"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Create"></td>
					<td><input type="button" onclick="location.href='AdminMenu.jsp';" value="Back"></td>
				</tr>
			</table>
		</form>
		<%
			if((User) session.getAttribute("NewUser") != null){
				User newUser = (User) session.getAttribute("NewUser");
		%>
			<table>
				<tr>
					<td>User:</td>
					<td><%=newUser.getIdNumber() %></td>
				</tr>
				<tr>
					<td>PIN:</td>
					<td><%=newUser.getPin() %></td>
				</tr>
				<tr>
					<td>Name:</td>
					<td><%=newUser.getName() %></td>
				</tr>
				<tr>
					<td>Gender</td>
					<td><%=newUser.getGender() %></td>
				</tr>
				<tr>
					<td>Contact Number:</td>
					<td><%=newUser.getContactNumber() %></td>
				</tr>
				<tr>
					<td>Address:</td>
					<td><%=newUser.getAddress() %></td>
				</tr>
				<tr>
					<td>Has been created</td>
				</tr>
				
			</table>
		<%
				session.removeAttribute("NewUser");
			}else{
				String result = (String) session.getAttribute("createResult");
	            if (result != null) {
	                out.println(result);
	                session.removeAttribute("createResult");
	            }
			}
		%>
	</div>
</body>
</html>