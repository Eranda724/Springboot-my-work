<%@page import="com.example.demo.entity.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%User u = (User)session.getAttribute("userobj"); %>
Welcome to your account <%=u.getName() %> !!!!
</body>
</html>