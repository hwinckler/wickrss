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

</head>
<body>
  <div class="container">

    <!-- Static navbar -->
    <nav class="navbar navbar-default ">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">FeedReader</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class=""><a href="signin.jsp">Home</a></li>
            <li class="active"><a href="about.jsp">About</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div><!--/.container-fluid -->
    </nav>

    <ol class="breadcrumb">
      <li><a href="signin.jsp">Home</a></li>
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
    
      <footer class="footer">
        <p>&copy; Company 2015</p>
      </footer>    

  </div> <!-- /container -->



  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="../js/bootstrap.min.js"></script>
</body>
</html>
