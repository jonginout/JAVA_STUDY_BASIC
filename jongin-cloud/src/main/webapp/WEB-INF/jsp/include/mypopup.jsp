<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="st-popup st-userpopup" id="user-popup">
	<div class="st-userpopup__cont">
		<div class="st-userpopup__main">
			<div class="st-userpopup__photo">
				<div class="st-userpopup__img"
					style="background-image: url('프로필 사진 URL 삽입');"></div>
				</div>
				
			<div class="st-userpopup__info">
				<div class="st-userpopup__name text-ellipsis">
					이름 <small>(@아이디)</small>
				</div>
				<div class="st-userpopup__status">
					<span class="label label-success">
						<c:choose>
							<c:when test="${user.status=='ADMIN'}">
								관리자 계정
							</c:when>
							<c:otherwise>
								일반 계정
							</c:otherwise>
						</c:choose>
					</span><!-- 학생/ 반장 -->
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
			<li><a href="#로그아웃 URL 삽입">
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