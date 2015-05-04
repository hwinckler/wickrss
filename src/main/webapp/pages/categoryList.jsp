<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="list-group">
	<c:if test="${fn:length(categories) > 0}">
	
		<c:forEach var="category" items="${categories}">
		
	      <li class="list-group-item">
		      <a href="#" class="lnk-update">${category.title}<span id="upd_id" style="display: none;">${category.id}</span><span id="upd_title" style="display: none;">${category.title}</span></a>
	
	        <p class="navbar-right">
	          <a href="#" class="lnk-delete"><span class="glyphicon glyphicon-trash">&nbsp;</span><span id="cat_id" style="display: none;">${category.id}</span></a>
	        </p>
	
	      </li>	
	
		</c:forEach>	
	
	</c:if>
	<c:if test="${fn:length(categories) == 0}">
		No itens to list!
	</c:if>
</div>