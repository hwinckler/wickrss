<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>RSSFeed-Leitor</title>
  <!-- Bootstrap -->
  <link href="../css/bootstrap.min.css" rel="stylesheet" media="screen">
  <link href="../css/normalize.css" rel="stylesheet" media="screen">
  <link href="css/geral.css" rel="stylesheet" media="screen">

</head>
<body>
  <div class="container">

	<c:import url="header.jsp?opt=about" />

    <ol class="breadcrumb">
      <li><a href="index">Home</a></li>
      <li class="active">About</li>
    </ol>

    <div class="jumbotron">
      <h1>RSSFeed Leitor v.1</h1>
      <p class="lead">Projeto de estudo para as tecnologias:</p>
      <div>
      	<ul>
      		<li>Bootstrap - HTML, CSS, and JS framework for developing responsive, mobile first.</li>
      		<li>Google Guice - Lightweight dependency injection (DI) framework for Java.</li>
      		<li>MyBatis - Persistence framework.</li>
      		<li>JUnit - Simple framework to write repeatable tests.</li>
      		<li>Rome - RSS and Atom utilities for Java.</li>
      		<li>DBUnit - JUnit extension targeted at database-driven projects.</li>
      		<li>Google+ Sign-In.</li>
      		<li>Git - Version control system.</li>
      		<li>Github - Online project hosting using Git.</li>
      	</ul>
      </div>
      
      <p class="lead">Colabore com o projeto em:</p>
      <div>
      	<ul>
      		<li><a href="https://github.com/hwinckler/rssfeed-leitor.git" target="_blank">https://github.com/hwinckler/rssfeed-leitor.git</a></li>
      	</ul>
      </div>
    </div>

  </div> <!-- /container -->



  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="../js/bootstrap.min.js"></script>
</body>
</html>
