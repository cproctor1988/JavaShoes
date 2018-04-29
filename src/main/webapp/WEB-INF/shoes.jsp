<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome!</title>
</head>
<body>
<h1>Welcome, ${currentUser.firstName}</h1>
    
    <form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" /></form>
        <a href="/home">Go To Dashboard</a>
    <fieldset><legend>All Products</legend>
		<table>
		<thead>
		<tr>
		<th>Product</th>
		<th>Seller</th>
		<th>Date Posted</th>
		<th>Amount</th>
		<th>Action</th>
		</tr>
		</thead>
		<c:forEach items= "${shoes}" var ="s">
			<tr>
			<td><c:out value="${s.name}"></c:out></td>
			<td><c:out value="${s.seller.firstName}"></c:out></td>
			<td><c:out value="${s.createdAt}"></c:out></td>
			<td><c:out value="${s.amount}"></c:out></td>
			<td><form method="POST" action="/buy/${s.id}">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<input type="submit" value="Buy"/></form><td>
			</tr>
			</c:forEach>
		</table>
		</fieldset>
</body>
</html>