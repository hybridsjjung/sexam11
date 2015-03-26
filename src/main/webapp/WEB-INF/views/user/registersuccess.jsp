<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/views/taglib.jspf" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>registersuccess.jsp</title>
</head>
<body>

<hr>
	<h1>등록 성공</h1>
<hr>

<br>
Error : ${error}
<br>

Name : ${requestScope.user.name} <br>
Age : ${user.age} <br>
Birthday : <fmt:formatDate value="${user.birthday}" pattern="yyyyMMdd"/> <br>

<a href="register">User 등록 화면으로...</a>
</body>
</html>