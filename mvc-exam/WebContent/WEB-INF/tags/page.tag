<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="data" type="com.jongin.common.PageResult" %>
<%@ attribute name="url"  type="java.lang.String" %>
<style type="text/css">
	.pageBtn:hover{
		cursor : pointer;
		background: skyblue;
	}
	.pageBtn{
		font-size: 20px;
	}
	.pageBtn>a{
		padding: 20px;
	}
</style>
			<c:if test="${data.count != 0 }">
			<nav>
				<ul class="pagination">
					<c:if test="${data.prev}">
						<li class="pageBtn">
							<a onclick="goPage(${data.beginPage-1})" aria-label="Previous"> 
								<span aria-hidden="true">&laquo;</span>
							</a>
<%-- 							<a href="${url}?pageNo=${data.beginPage-1}" aria-label="Previous">  --%>
<!-- 								<span aria-hidden="true">&laquo;</span> -->
<!-- 							</a> -->
						</li>
					</c:if>
					<c:forEach var="i" begin="${data.beginPage}" end="${data.endPage}">
						<c:choose>
							<c:when test="${pageNo==i}">
								<li class="pageBtn" class="active">
									<a href="#${i}">${i}</a>
								</li>						
							</c:when>
							<c:otherwise>
								<li class="pageBtn">
									<a onclick="goPage(${i})">${i}</a>
<%-- 									<a href="${url}?pageNo=${i}">${i}</a> --%>
								</li>						
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${data.next}">
						<li class="pageBtn">
							<a onclick="goPage(${data.endPage+1})" aria-label="Next">
								<span aria-hidden="true">&raquo;</span>
							</a>
<%-- 							<a href="${url}?pageNo=${data.endPage+1}" aria-label="Next"> --%>
<!-- 								<span aria-hidden="true">&raquo;</span> -->
<!-- 							</a> -->
						</li>
					</c:if>
				</ul>
			</nav>
			</c:if>
			
			
			
<script type="text/javascript">
	
	var url = "";
	var param = '${param.year}';
	if (param.length>0) {
		url = `
		&staffName=${param.staffName}&religionCode=${param.religionCode}&year=${param.year}&month=${param.month}&day=${param.day}&year2=${param.year2}&month2=${param.month2}&day2=${param.day2}&graduateDay1=${param.graduateDay1}&graduateDay2=${param.graduateDay2}
		`;		
	}
	
	function goPage(pageNo) {
		location.href =
			"${url}?pageNo="+pageNo+url;
	}
</script>