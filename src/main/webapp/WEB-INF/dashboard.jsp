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
    <a href="/products">all Products</a>
    <form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" /></form>
        
        <form method="POST" action="/sell">
		<p><label for="sell"></label>
	    <input type="text" id="name" name="name" placeholder="Description of shoe"/></p>
	    <input type="long" id="amount" name="amount" placeholder="how much ya want?"/></p>
	    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	    <input type="hidden" name="user_id" value="<c:out value="${currentUser.id}"></c:out>"/>
		<input type="submit" value="Sell!"/></form>
		
		<fieldset><legend>Your products that are not yet sold</legend>
		<table>
		<thead>
		<tr>
		<th>Product</th>
		<th>Date Posted</th>
		<th>Amount</th>
		<th>Action</th>
		</tr>
		</thead>
		
		<c:forEach items= "${notsold}" var ="n">
			<tr>
			<td><c:out value="${n.name}"></c:out></td>
			<td><c:out value="${n.createdAt}"></c:out></td>
			<td><c:out value="${n.amount}"></c:out></td>
			<td><form method="POST" action="/remove/${n.id}">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<input type="submit" value="remove"/></form>
			</td>
			</tr>
			</c:forEach>
		</table>
		</fieldset>
		<fieldset><legend>Your Sales</legend>
		<table>
		<thead>
		<tr>
		<th>Product</th>
		<th>Date Bought</th>
		<th>Buyer</th>
		<th>Amount</th>
		</tr>
		</thead>
		
		<c:forEach items= "${sold}" var ="s">
			<tr>
			<td><c:out value="${s.name}"></c:out></td>
			<td><c:out value="${s.soldAt}"></c:out></td>
			<td><c:out value="${s.buyer.firstName}"></c:out></td>
			<td><c:out value="${s.amount}"></c:out></td>
			
			</tr>
			</c:forEach>
		</table>
		</fieldset>
		</fieldset>
		<fieldset><legend>Your Purchases</legend>
		<table>
		<thead>
		<tr>
		<th>Product</th>
		<th>Date Bought</th>
		<th>Seller</th>
		<th>Amount</th>
		</tr>
		</thead>
		<c:forEach items= "${bought}" var ="s">
			<tr>
			<td><c:out value="${s.name}"></c:out></td>
			<td><c:out value="${s.soldAt}"></c:out></td>
			<td><c:out value="${s.seller.firstName}"></c:out></td>
			<td><c:out value="${s.amount}"></c:out></td>
			</tr>
			</c:forEach>
		</table>
		</fieldset>
	
   
</body>
</html>