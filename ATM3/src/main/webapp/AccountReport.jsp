<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Services.* , java.util.List, Models.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Account report</title>
</head>
 <%= Constants.TABLE_STYLE %>
<body>
	<div align="center">
		<% 
			Utilities utils = new Utilities();
			AdminServices adminServices = new AdminServices();
			User admin = (User)session.getAttribute("admin");
		%>
		
		Account report
		<table border="1" cellpadding="5" cellspacing="5">
			<tr>
				<th>Name</th>
    			<th>User ID</th>
    			<th>PIN</th>
    			<th>Contact number</th>
    			<th>Gender</th>
			</tr>
			<% 
					List<User> userList = adminServices.accountReport();
					for(User user : userList){
						
					
			%>
			<tr>
				<td><%=user.getName()%></td>
				<td><%=utils.padLeftZeros(user.getIdNumber(), Constants.ID_NUMBER_LENGTH) %></td>
				<td><%=utils.padLeftZeros(user.getPin() , Constants.ID_PIN_LENGTH)%></td>
				<td><%=user.getContactNumber() %></td>
				<td><%=user.getGender() %></td>
			</tr>
			<%
					}
			%> 
			</table>
<%-- 			<c:forEach var="user" items = "${userList}">
				<tr>
					<td>${user.name}</td>
					<td>${user.idNumber}</td>
					<td>${user.pin}</td>
					<td>${user.contactNumber}</td>
					<td>${user.gender}</td>
				</tr>
			</c:forEach>
		</table>
		
		<c:if test="${currentPage != 1}"> 
      		<td><a href="AccountReport?page=${currentPage - 1}">Previous</a></td> 
  		</c:if> 
  		
  		<table border="1" cellpadding="5" cellspacing="5"> 
      		<tr> 
          		<c:forEach begin="1" end="${numberOfPages}" var="i"> 
              		<c:choose> 
                  		<c:when test="${currentPage eq i}"> 
                      		<td>${i}</td> 
                  		</c:when> 
                  		<c:otherwise> 
                      		<td><a href="AccountReport?page=${i}">${i}</a></td> 
                  		</c:otherwise> 
              		</c:choose> 
          		</c:forEach> 
      		</tr> 
  		</table>
  
	  <c:if test="${currentPage lt numberOfPages}"> 
	      <td><a href="AccountReport?page=${currentPage + 1}">Next</a></td> 
	  </c:if>  --%>
  
		<input type="button" onclick="location.href='AdminMenu.jsp';" value="Back">
	</div>
</body>
</html>