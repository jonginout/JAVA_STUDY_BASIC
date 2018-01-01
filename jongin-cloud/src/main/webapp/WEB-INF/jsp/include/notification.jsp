<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/notifications/notifications.css">


<div class="st-spanel" id="side-bar">
<div class="st-spanel__tabs">
  <ul class="nav">
    <li class="active alt"><a href="#settings" data-toggle="tab" tabindex="-1">앱 메뉴</a></li>
    <li class="header-alarm-btn"><a href="#notifications" data-toggle="tab" tabindex="-1">내 알람</a></li>
    <li class="st-spanel__close"><a class="fa fa-long-arrow-right" tabindex="-1"></a></li>
  </ul>
</div>
<div class="st-spanel__content"> 
  <div class="tab-content">
  
  	<!-- 앱 설정 부분 탭 -->
    <div class="tab-pane fade in active" id="settings">
      <div class="st-spanel__settings">
        <div class="st-spanel__scroll scrollbar">
          <div class="st-settings" id="app-settings">
            <div class="st-settings__list">
              <a class="st-settings__item">
                <div class="st-settings__row">
                  <div class="st-settings__label"><b>앱 메뉴</b></div>
                  <div class="st-settings__control"><i class="fa fa-spin fa-circle-o-notch st-settings__loader"></i></div>
                </div>
              </a>
              <a class="st-settings__item">
                <div class="st-settings__row">
                  <div class="st-settings__label">온라인 모드<!-- 오프라인 모드 --></div>
                  <div class="st-settings__control">
                    <input id="app-settings__site" type="checkbox" name="site" checked="checked">
                  </div>
                </div>
              </a>
              <a class="st-settings__item" href="${pageContext.request.contextPath}/cloud/cloud.do">
                <div class="st-settings__row">
                  <div class="st-settings__label">
                  	내 클라우드 <i class="fa fa-cloud-download"></i>
                  </div>
                  <div class="st-settings__control text-nowrap"><i class="fa fa-angle-right st-settings__ico"></i></div>
                </div>
              </a>
              <a class="st-settings__item" href="${pageContext.request.contextPath}/forum/forum.do">
                <div class="st-settings__row">
                  <div class="st-settings__label">
                  	개발자 포럼
                  </div>
                  <div class="st-settings__control text-nowrap"><i class="fa fa-angle-right st-settings__ico"></i></div>
                </div>
              </a>
              <a class="st-settings__item">
                <div class="st-settings__row">
                  <div class="st-settings__label">
                    문의하기
                  </div>
                  <div class="st-settings__control text-nowrap"><i class="fa fa-angle-right st-settings__ico"></i></div>
                </div>
              </a>
              <a class="st-settings__item" href="">
                <div class="st-settings__row">
                  <div class="st-settings__label">
                    INFORMATION
                  </div>
                  <div class="st-settings__control text-nowrap"><i class="fa fa-angle-right st-settings__ico"></i></div>
                </div>
              </a>
            </div>
          </div>

          </div>
        </div>
      </div>
      
      
      <!-- 알람 설정 부분 탭 -->
      <div class="tab-pane fade" id="notifications">
        <div class="st-notifications">
          <div class="st-notifications__head">
            <div class="fluid-cols">
              <div class="expand-col">
                <div class="st-notifications__title">새 알람</div>
                <small>읽지 않은 알람 <span class="st-notifications__count">0</span>개</small>
              </div>
              <div class="min-col">
                <button class="btn btn-default btn-sm st-notifications__all">모두 읽음</button>
              </div>
            </div>
          </div>
          <div class="st-notifications__list">
            <div class="st-spanel__scroll scrollbar">
              <div class="alarm-list"></div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>


<script src="${pageContext.request.contextPath}/js/notification/notification.js"></script>