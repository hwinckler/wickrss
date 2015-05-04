<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<select class="form-control" id="category">

<c:if test="${fn:length(categories) > 0}">

    	<c:forEach var="category" items="${categories}">
      		<option value="${category.id}" ${categoryID == category.id ? 'selected=\'selected\'' : ''}>${category.title}</option>
      	</c:forEach>	

</c:if>
</select>
