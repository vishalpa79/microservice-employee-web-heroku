
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login</title>
	</head>
	<body> <div align="center">
	
		<font color="red">${message}</font>
		<form:form id="loginForm" method="post" action="login.html" commandName="employeeBean">
<div align="center">
<table>
	<tr>
		<td>	<form:label path="userName">Enter your user-name</form:label></td>
		<td>	<form:input id="userName" name="userName" path="" /><br></td>
			</tr>
			<tr>
			<td><form:label path="emailId">Enter user emailId </form:label></td>
			
			<td><form:input id="emailId" name="emailId" path="" /><br></td></tr>
			<tr>
		<td colspan="2">
			<input type="submit" value="Add Contact"/>
		</td>
			</tr>
</table>	
	</div>
		</form:form>
	 </div></body>
</html>
