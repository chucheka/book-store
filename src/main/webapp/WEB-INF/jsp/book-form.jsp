<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>     
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link href="/css/main.css" rel="stylesheet"></link>
 <title>Book Form</title>
</head>
<body style="background-color: #FFFFE0;">
 <div class="container">
  <spring:url value="/book/addBook" var="addURL" />
  <form:form modelAttribute="book" method="post" action="${addURL}" cssClass="form" >
  <h2>Add A Book</h2>
   <form:hidden path="bookId"/>
   <div class="form-group">
    <form:input path="title" cssClass="form-control" id="title" placeholder="Title"/>
   </div>
   <div class="form-group">
    <form:input path="author" cssClass="form-control" id="author" placeholder="Author" />
   </div>
   <div class="form-group">
    <form:input path="isbn" cssClass="form-control" id="isbn" placeholder="ISBN"/>
   </div>
   <div class="form-group">
    <form:input path="publisher" cssClass="form-control" id="publisher" placeholder="Publisher" />
   </div>
   <div class="form-group">
    <form:input path="country" cssClass="form-control" id="ountry"  placeholder="Country"/>
   </div>
   <div class="form-group">
    <form:input path="genre" cssClass="form-control" id="genre" placeholder="Genre"/>
   </div>
   <button type="submit" class="btn">Add Book</button>
  </form:form>
  
 </div>
</body>
</html>