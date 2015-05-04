<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="list-group">
	<c:if test="${fn:length(feeds) > 0}">
		<c:forEach var="feed" items="${feeds}">
			<a href="#" class="list-group-item ${feed.visualized ? '' : 'list-group-item-info'} lnk-feed">
				<h4 class="list-group-item-heading">${feed.title}</h4>
				<p class="list-group-item-text">${feed.description}</p>
				<span id="link" style="display: none;">${feed.link}</span>
				<span id="feed_id" style="display: none;">${feed.id}</span>
			</a>
		</c:forEach>	
	</c:if>
	<c:if test="${fn:length(feeds) == 0}">
		No itens to list!
	</c:if>
</div>