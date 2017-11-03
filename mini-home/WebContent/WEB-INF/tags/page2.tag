<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="data" type="common.PageResult" %>

<c:if test="${data.count != 0}">
	<nav>
	  <ul class="pagination">
	    <li>
 	    <c:if test="${data.prev}"> 
	      <a href="<c:choose><c:when test="${data.prev}">${pageContext.request.contextPath}${data.servletPath}${data.beginPage - 1}</c:when>
	      <c:otherwise>#1</c:otherwise></c:choose>"aria-label="Previous">
	        <span aria-hidden="true">&laquo;</span>
	      </a>
 	    </c:if> 
	    </li>
	    <c:forEach var="i" begin="${data.beginPage}" end="${data.endPage}">
	    	<c:choose>
	    		<c:when test="${i eq data.pageNo}">
	    			<li class="active"><a href="#${i}">${i}</a></li>
	    		</c:when>
	    		<c:otherwise>
	    			<li><a href="${pageContext.request.contextPath}${data.servletPath}${i}">${i}</a></li>
	    		</c:otherwise>
	    	</c:choose>
	    </c:forEach>
	    
	    <li>
 	    <c:if test="${data.next}"> 
	      <a href="${pageContext.request.contextPath}${data.servletPath}${data.endPage + 1}" aria-label="Next">
	        <span aria-hidden="true">&raquo;</span>
	      </a>
 	    </c:if> 
	    </li>
	  </ul>
	</nav>
</c:if>
