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
  <script src="js/feed.js"></script>
    
</head>
<body>
  <div class="container">

	<c:import url="header.jsp?opt=feed" />
    
    <ol class="breadcrumb">
      <li><a href="index">Home</a></li>
      <li class="active">Feeds</li>
    </ol>
    
    <form>
      <div class="form-group">
        <label for="feed">Feed</label>
        <input type="text" class="form-control txt-link" id="link" name="link" placeholder="http://site.com/feed" value=""><span class="link-loading"></span>
        
        <label for="feed">Title</label>
        <input type="text" class="form-control" id="title" name="title" value="" disabled="disabled">
        
        <label for="feed">Description</label>
        <input type="text" class="form-control" id="description" name="description" value="" disabled="disabled">
                        
        <label class="control-label" for="category">Category</label>
		<div id="category_content">
		</div>
		
		<input type="hidden" id="id" name="id" value="">
      </div>

      <button type="button" class="btn btn-default btn-clear">clear</button>
      <button type="button" class="btn btn-primary btn-add" disabled="disabled">add</button>
      
     </form>
    <br>
    <ul class="list-group" id="channel_content">

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
