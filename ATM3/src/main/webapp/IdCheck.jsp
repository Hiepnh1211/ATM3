<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1" import="Services.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ID Check</title>
</head>
<body>
 <div align="center">
 <h1>ATM Login Form</h1>
  <form action="IdCheck" method="post">
   <table style="with: 100%">
    <tr>
     <td>User ID</td>
     <td><input type="text" maxlength="8" name="username" /></td>
    </tr>
    <tr>
   </table>
   <input type="submit" value="Submit" />
  </form>
  		<%
            String result = (String) session.getAttribute("result");
            if (result != null) {
                out.println(result);
            }
        %>
 </div>
</body>
</html>