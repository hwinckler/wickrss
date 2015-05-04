<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
            <li class="${param.opt == 'index' ? 'active' : ''}"><a href="index">Home</a></li>
            <li class="${param.opt == 'about' ? 'active' : ''}"><a href="about">About</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
          <li class="${param.opt == 'category' ? 'active' : ''}">
                  <a href="category">Category</a>
       
          </li>
          <li class="${param.opt == 'feed' ? 'active' : ''}">

                  <a href="feed">Feed</a>
        
          </li>
             <li>
      
				<div id="auth-display-img">
					<img style='height:100%;' src='${sessionScope.user.picture}'/>
				</div>
				
            </li>
             <li>

					<a href="logout">${sessionScope.user.email}</a>
				
            </li>            
		</ul>
        </div><!--/.nav-collapse -->
      </div><!--/.container-fluid -->
    </nav>