<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<link href="/css/main.css" rel="stylesheet"></link>
	<title>Home Page</title>
	<style>
	table {
	  font-family: arial, sans-serif;
	  border-collapse: collapse;
	  width: 80%;
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
  <h2>BOOK LIST</h2>
  <table class="table table-striped">
   <thead>
    <th>#BOOKID</th>
    <th>TITLE</th>
    <th>AUTHOR</th>
    <th>ISBN</th>
    <th>PUBLISHER</th>
    <th>COUNTRY</th>
    <th>GENRE</th>
    <th>&nbsp;</th>
    <th>&nbsp;</th>
    <th>&nbsp;</th>
    
   </thead>
   <tbody>
    <c:forEach items="${bookList }" var="book" >
     <tr>
      <td>${book.bookId}</td>
      <td>${book.title }</td>
      <td>${book.author }</td>
      <td>${book.isbn }</td>
      <td>${book.publisher }</td>
      <td>${book.country }</td>
      <td>${book.genre }</td>
      <td>
       <spring:url value="/updatebook/${book.bookId }" var="updateURL" />
       <a class="btn-link" href="${updateURL }" >Update</a>
      </td>
      <td>
       <spring:url value="/book/deleteBook/${book.bookId }" var="deleteURL" />
       <a class="btn-link delete" href="${deleteURL }" >Delete</a>
      </td>
      <td>
       <spring:url value="/book/${book.bookId}" var="getBookURL" />
       <a class="btn-link " href="${getBookURL}" >View</a>
      </td>
     </tr>
    </c:forEach>
   </tbody>
  </table>
  <spring:url value="/book/addBook/" var="addURL"/>
  <a href="${addURL }" class="btn" style="margin-top:30px">Add New Book</a>
 </div>
</body>
</html>
