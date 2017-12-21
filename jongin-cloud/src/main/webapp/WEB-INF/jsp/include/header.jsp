<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<div class="st-header" class="navbar-fixed-top">
  <div class="fluid-cols">

    <div class="col-xs-8 col-sm-6 header-title-btn">
      <div class="st-header__title">
      	<span><strong><a href="${pageContext.request.contextPath}/cloud/cloud.do">
      		CLOUD OF JONGIN <i class="fa fa-cloud-download" aria-hidden="true"></i>
      	</a></strong></span>
      </div>
    </div>
    
<!--     <div class="hidden-xs col-sm-4 col-sm-push-1"> -->
<!-- 	    <div id="imaginary_container">  -->
<!--            <div class="input-group stylish-input-group"> -->
<!--                <input type="text" class="form-control"  placeholder="Search" id="searchBar"> -->
<!--                <span class="input-group-addon"> -->
<!--                    <button type="submit"> -->
<!--                        <span class="glyphicon glyphicon-search"></span> -->
<!--                    </button>   -->
<!-- 			   </span> -->
<!--            </div> -->
<!--        </div> -->
<!--     </div> -->

    <div class="col-xs-4 col-sm-6 header-icon-btn ">
      <ul class="st-header__actions text-nowrap">        
	    <c:choose>
	    	<c:when test="${empty user}">
	    		<a href="${pageContext.request.contextPath}/login/loginform.do" style="color:white !important;">
			        <li class="st-header__act">
			       		로그인 <i class="fa fa-sign-out"></i>
			        </li>		
		        </a>	    		
	    	</c:when>
	    	<c:otherwise>
	    		<li class="st-header__act st-spanel__open st-header__notifications" data-tab="#notifications"><i class="fa fa-bell"></i>
		          <div class="st-header__count st-notifications-count"><strong>9</strong></div> <!-- 알람 갯수 99개 최대 설정-->
		        </li>
		        <li class="st-header__act st-spanel__open st-header__settings" data-tab="#settings"><i class="fa fa-cog"></i></li>
		        <li class="st-header__act st-header__user" data-toggle="popup" data-target="#user-popup"><i class="fa fa-user"></i>
					<%@ include file="/WEB-INF/jsp/include/mypopup.jsp" %>
		        </li>	
	    	</c:otherwise>
	    </c:choose>
      </ul>
    </div>
    
  </div>
</div> 

<%@ include file="/WEB-INF/jsp/include/notification.jsp" %>


<script>
    
//     $( function() {
//       var availableTags = [
//         "ActionScript",
//         "AppleScript",
//         "Asp",
//         "BASIC",
//         "C",
//         "C++",
//         "Clojure",
//         "COBOL",
//         "ColdFusion",
//         "Erlang",
//         "Fortran",
//         "Groovy",
//         "Haskell",
//         "Java",
//         "JavaScript",
//         "Lisp",
//         "Perl",
//         "PHP",
//         "Python",
//         "Ruby",
//         "Scala",
//         "김종인"
//       ];
//       $( "#searchBar" ).autocomplete({
//         source: availableTags,
//       });
//       $(document).scroll(function(){
//         $( "#searchBar" ).autocomplete( "close" );
//       })

//     });
    
</script>