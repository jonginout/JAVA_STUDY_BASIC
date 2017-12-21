<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="st-popup st-userpopup" id="user-popup">
	<div class="st-userpopup__cont">
		<div class="st-userpopup__main">
			<div class="st-userpopup__photo">
				<c:choose>
					<c:when test="${user.pass=='NAVER'}">
						<div class="st-userpopup__img"
							style="background-image: url('${user.profileImg}');"></div>
						</div>
					</c:when>
					<c:otherwise>
						<div class="st-userpopup__img"
							style="background-image: url('${pageContext.request.contextPath}/common/down.do?ext=img&path=${user.profileImg}');"></div>
						</div>
					</c:otherwise>
				</c:choose>
			<div class="st-userpopup__info">
				<div class="st-userpopup__name text-ellipsis">
					${user.name} <small>(${user.id})</small>
				</div>
				<div class="st-userpopup__status">
					<span class="label label-success">
						${user.maxVolume/1024/1024} MB 소유
					</span>
				</div>
			</div>
		</div>
		<ul class="st-userpopup__menu">
			<li class="st-spanel__open" data-tab="#notifications" data-toggle="popup" data-target="#user-popup" data-method="hide">
				<a href="#내피드 URL 삽입">
					<div class="fluid-cols">
						<div class="expand-col text-ellipsis">
							<span>내 타임라인</span>
						</div>
						<div class="min-col">
							<i class="fa fa-newspaper-o"></i>
						</div>
					</div>
			</a></li>
			<li data-toggle="popup" data-target="#user-popup" data-method="hide"><a
				href="#내정보 URL 삽입">
					<div class="fluid-cols">
						<div class="expand-col text-ellipsis">
							<span>내 정보</span>
						</div>
						<div class="min-col">
							<i class="fa fa-user"></i>
						</div>
					</div>
			</a></li>
			<li><a href="${pageContext.request.contextPath}/login/logout.do">
					<div class="fluid-cols">
						<div class="expand-col text-ellipsis">
							<span>로그아웃</span>
						</div>
						<div class="min-col">
							<i class="fa fa-sign-out"></i>
						</div>
					</div>
			</a></li>
		</ul>
	</div>
</div>
