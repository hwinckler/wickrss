<%@page import="br.com.wickrss.user.model.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt_BR">
<head>
  <meta charset="utf-8">
  <title>WickRSS</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Bootstrap -->
  <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
  <link href="css/normalize.css" rel="stylesheet" media="screen">
 
</head>
<body>

<%
    User user = (User) session.getAttribute("user");

	if(user != null){
		
%>
<script type="text/javascript">
location.href = "index";
</script>
<%
		
	}
%>

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
          <a class="navbar-brand" href="index">WickRSS</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="signin.jsp">Home</a></li>
            <li class=""><a href="about.jsp">About</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div><!--/.container-fluid -->
    </nav>
  
      <div class="jumbotron">
        <h1>WickRSS</h1>
        <p class="lead">WickRSS é um simples leitor Web e Mobile de feed RSS... blá blá blá...</p>
        <p><a href="login?act=login"><img alt="sign-in" src="img/Red-signin_Long_base_20dp.png"></a></p>
      </div>  
      
      <div class="row marketing">
        <div class="col-lg-6">
          <h4>Categorias</h4>
          <p>Crie categorias para melhor organizar os seus feed RSS.</p>
          <p>Tela 1 do cadastro de categoria.</p>

          <h4>Feed Rss</h4>
          <p>Cadastre os feed RSS de seus sites favoritos e vincule-os a uma categoria.</p>
          <p>Tela 1 do cadastro de feed.</p>

        </div>

        <div class="col-lg-6">
          <h4>Web e Mobile</h4>
          <p>Com um design simple e leve, acesse-o através do seu navegador ou dispositivo móvel.</p>

          <h4>Telas</h4>
          <p>Tela 1.</p>
          <p>Tela 2.</p>

        </div>
      </div>      
  
	<br>
	${erro}

      <footer class="footer">
        <p>&copy; Company 2015</p>
      </footer>

  </div> <!-- /container -->



	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>

</html>
