<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="net.polarisdigitech.book_store.book_store.entity.Book" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<link href="/css/main.css" rel="stylesheet"></link>
	<title>View Book</title>
	<style>
	table {
	  font-family: arial, sans-serif;
	  border-collapse: collapse;
	  width: 60%;
	}
	
	td, th {
	  border: 1px solid #dddddd;
	  text-align: left;
	  padding: 8px;
	}
	</style>
	</head>
<body style="background-color: #FFFFE0;">

<div class="container">
	<%
	
	Book book = (Book)request.getAttribute("book");
	Long theBookId = (Long)request.getAttribute("bookId");
	%>
	
	<c:choose>
		<c:when test="${empty book}">
		<h2>Book with Book ID:${theBookId} doesn't exist</h2>
		</c:when>
		<c:otherwise>
		<h2>Book Details</h2>
		<div>
			<span><em>${book.title}</em></span> is a <span><em>${book.genre}&nbsp;</em>book</span> written by <span><em>${book.author}</em></span> published by 
			<span><em>${book.publisher}</em></span>,<span><em>&nbsp;${book.country}</em></span> with ISBN:<span><em>${book.isbn}</em></span>
		</div>
		</c:otherwise>
	</c:choose>
	
<a href="/book/list" class="link"><< &nbsp;Book To List</a>
</div>
   
</body>
</html>
