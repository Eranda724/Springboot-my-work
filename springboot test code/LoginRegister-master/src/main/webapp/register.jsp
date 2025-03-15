<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="/register" method="post">
  <input type="email"  name="email" placeholder="Enter email"></input>
  <input type="text" name="name" placeholder="Enter your name"></input>
  <input type="password" name="pass" placeholder="Enter password"></input>
  password must contain a capital letter, a small letter and a number
  <input type="submit" value="submit"></input>
  <%String name = (String)request.getAttribute("name"); %>
  ${msg}
  <%if(name!=null){%>
   ${name} registered succesfully...
  <%} %>
</form>
</body>
</html>