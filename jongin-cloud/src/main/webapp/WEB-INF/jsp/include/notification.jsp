<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<div class="st-spanel">
<div class="st-spanel__tabs">
  <ul class="nav">
    <li class="active alt"><a href="#settings" data-toggle="tab" tabindex="-1">앱 설정</a></li>
    <li><a href="#notifications" data-toggle="tab" tabindex="-1">내 알람</a></li>
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
              <div class="st-settings__item">
                <div class="st-settings__row">
                  <div class="st-settings__label"><b>앱 설정</b></div>
                  <div class="st-settings__control"><i class="fa fa-spin fa-circle-o-notch st-settings__loader"></i></div>
                </div>
              </div>
              <div class="st-settings__item">
                <div class="st-settings__row">
                  <div class="st-settings__label">온라인 모드<!-- 오프라인 모드 --></div>
                  <div class="st-settings__control">
                    <input id="app-settings__site" type="checkbox" name="site" checked="checked">
                  </div>
                </div>
              </div>
              <div class="st-settings__item">
                <div class="st-settings__row">
                  <div class="st-settings__label">
                  	내 클라우드 <i class="fa fa-cloud-download"></i>
                  </div>
                  <div class="st-settings__control text-nowrap"><i class="fa fa-angle-right st-settings__ico"></i></div>
                </div>
              </div>
              <div class="st-settings__item">
                <div class="st-settings__row">
                  <div class="st-settings__label">
                  	이모티콘
                  </div>
                  <div class="st-settings__control text-nowrap"><i class="fa fa-angle-right st-settings__ico"></i></div>
                </div>
              </div>
              <div class="st-settings__item">
                <div class="st-settings__row">
                  <div class="st-settings__label">
                    채팅목록
                  </div>
                  <div class="st-settings__control text-nowrap"><i class="fa fa-angle-right st-settings__ico"></i></div>
                </div>
              </div>
              <div class="st-settings__item">
                <div class="st-settings__row">
                  <div class="st-settings__label">
                    내 정보 수정
                  </div>
                  <div class="st-settings__control text-nowrap"><i class="fa fa-angle-right st-settings__ico"></i></div>
                </div>
              </div>
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

              <div class="st-notification new"> <!-- new 클래스 하나 차이 new 클래스가 있는게 안읽은 것-->
                <div class="st-notification__main">
                  <div class="st-notification__title"><b>System</b><small>25m ago</small>
                    <div class="st-notification__mark"><i class="fa fa-check"></i></div>
                  </div>
                  <p>Lorem ipsum dolor sit.</p>
                </div>
              </div>
              <div class="st-notification">
                <div class="st-notification__main">
                  <div class="st-notification__title"><b>Account</b><small>25 Feb 21:30</small>
                    <div class="st-notification__mark"><i class="fa fa-check"></i></div>
                  </div>
                  <p><a href="">John Doe</a> has been registered.</p>
                </div>
              </div>


            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>