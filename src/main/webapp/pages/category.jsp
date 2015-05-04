<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>RSSFeed-Leitor</title>
  <!-- Bootstrap -->
  <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
  <link href="css/normalize.css" rel="stylesheet" media="screen">
  <link href="css/geral.css" rel="stylesheet" media="screen">
  
  <script src="js/global.js"></script>
  <script src="js/category.js"></script>
    
</head>
<body>
  <div class="container">

	<c:import url="header.jsp?opt=category" />
    
    <ol class="breadcrumb">
      <li><a href="index">Home</a></li>
      <li class="active">Categories</li>
    </ol>
    
    <form>
      <div class="form-group">
        <label for="category">Category</label>
        <input type="text" class="form-control" id="title" name="title" placeholder="Java" value="">
        <input type="hidden" id="id" name="id" value="">
      </div>
      
      <button type="button" class="btn btn-default btn-clear">clear</button>
      <button type="button" class="btn btn-primary btn-add">add</button>

    </form>
    <br>
    <ul class="list-group" id="category_content">

		<img src="img/ajax-loader.gif" class="loading" />

    </ul>

	<br>
	${erro}

  </div> <!-- /container -->



  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
	<script>
		$(document).ready(function() {
			init();
		});
	</script>  
</body>
</html>
