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

	function goPage(pageNo) {
		
		var url = location.href;
		var uri = url.split(".do?");
		
		var param = "";
		
		if(uri.length>1){
			param = uri[1];
			if(param.indexOf('pageNo=')!=-1){
				if (param.indexOf('&')!=-1) {
					//페이지 파라미터 외에 다른 파라미터가 많을때 
					tmp = param.substring((param.indexOf("&")));
					param = "pageNo="+pageNo+tmp;
				}else{
					//페이지 파라미터 혼자 있을때
					param = "pageNo="+pageNo;
				}
			}else{
				// 페이지 파라미터가 없을때
				param = "pageNo="+pageNo+"&"+param;
			}
		}else{
			param = "pageNo="+pageNo;
		}
		
		
		location.href =
			"${url}?"+param;
	}
</script>