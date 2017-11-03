<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
			<c:if test="${pageResult.count != 0 }">
			<nav>
				<ul class="pagination">
					<c:if test="${pageResult.prev}">
						<li>
							<a href="list4.do?pageNo=${pageResult.beginPage-1}" aria-label="Previous"> 
								<span aria-hidden="true">&laquo;</span>
							</a>
						</li>
					</c:if>
					<c:forEach var="i" begin="${pageResult.beginPage}" end="${pageResult.endPage}">
						<c:choose>
							<c:when test="${pageNo==i}">
								<li class="active">
									<a href="#${i}">${i}</a>
								</li>						
							</c:when>
							<c:otherwise>
								<li>
									<a href="${pageContext.request.contextPath}/board/list4.do?pageNo=${i}">${i}</a>
								</li>						
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${pageResult.next}">
						<li>
							<a href="list4.do?pageNo=${pageResult.endPage+1}" aria-label="Next">
								<span aria-hidden="true">&raquo;</span>
							</a>
						</li>
					</c:if>
				</ul>
			</nav>
			</c:if>