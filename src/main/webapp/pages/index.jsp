<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt_BR">
<head>
  <meta charset="utf-8">
  <title>RSSFeed-Leitor</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Bootstrap -->
  <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
  <link href="css/normalize.css" rel="stylesheet" media="screen">
  <link href="css/geral.css" rel="stylesheet" media="screen">

  <script src="js/global.js"></script>
  <script src="js/index.js"></script>
  
</head>
<body>
  <div class="container">
  
	<c:import url="header.jsp?opt=index" />

    <div class="row">
      <div class="col-md-4">


        <div class="list-group categories">
  			<c:forEach var="category" items="${categories}" varStatus="status">
            	<a href="#" class="list-group-item ${status.first ? 'active' : '' } lnk-category" id="${category.id}">${category.title} <span class="badge">${category.unread}</span><span id="cat_id" style="display: none;">${category.id}</span></a>
  			</c:forEach>
        </div>

      </div>
      
      <div class="col-md-8">
                        <p class="">
                  <a class="btn btn-primary mark-all-as-read" href="#" role="button">Mark All as Read</a>
                  <a class="btn btn-primary sync-all" href="#" role="button">Sync All</a><span id="sync_loading"></span>
                </p>      
      <div id="feed_content">
      <img src="img/ajax-loader.gif" class="loading" />
      </div>
		
      </div>
    </div>


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
