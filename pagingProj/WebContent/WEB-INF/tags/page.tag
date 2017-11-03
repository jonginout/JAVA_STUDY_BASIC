<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="data" type="common.PageResult" %>


			<c:if test="${data.count != 0 }">
			<nav>
				<ul class="pagination">
					<c:if test="${data.prev}">
						<li>
							<a href="list5.do?pageNo=${data.beginPage-1}" aria-label="Previous"> 
								<span aria-hidden="true">&laquo;</span>
							</a>
						</li>
					</c:if>
					<c:forEach var="i" begin="${data.beginPage}" end="${data.endPage}">
						<c:choose>
							<c:when test="${pageNo==i}">
								<li class="active">
									<a href="#${i}">${i}</a>
								</li>						
							</c:when>
							<c:otherwise>
								<li>
									<a href="${pageContext.request.contextPath}/board/list5.do?pageNo=${i}">${i}</a>
								</li>						
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${data.next}">
						<li>
							<a href="list5.do?pageNo=${data.endPage+1}" aria-label="Next">
								<span aria-hidden="true">&raquo;</span>
							</a>
						</li>
					</c:if>
				</ul>
			</nav>
			</c:if>