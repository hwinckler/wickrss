<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="list-group">
	<c:if test="${fn:length(channels) > 0}">
    	<c:forEach var="channel" items="${channels}">
	      <li class="list-group-item">
	      <a href="#" class="lnk-update">${channel.link} <br> ${channel.title} - ${channel.description}
	      	<span id="upd_id" style="display: none;">${channel.id}</span>
	      	<span id="upd_title" style="display: none;">${channel.title}</span>
	      	<span id="upd_link" style="display: none;">${channel.link}</span>
	      	<span id="upd_description" style="display: none;">${channel.description}</span>
	      </a>
	
	        <p class="navbar-right">
	          <a href="#" class="link-delete"><span class="glyphicon glyphicon-trash">&nbsp;</span><span id="chan_id" style="display: none;">${channel.id}</span></a>
	        </p>
	
	      </li>    	
    	</c:forEach>	
	</c:if>
	<c:if test="${fn:length(channels) == 0}">
		No itens to list!
	</c:if>
</div>