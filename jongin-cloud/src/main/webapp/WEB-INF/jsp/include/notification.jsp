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
                  <div class="st-settings__label" id="lock-label">잠금 해제 <i class="fa fa-unlock" aria-hidden="true"></i></div>
                  <div class="st-settings__control" id="lock-mode">
                      <div class="material-switch pull-right">
                          <input id="lock-check" name="site" type="checkbox"/>
                          <label for="lock-check" class="label-primary"></label>
                      </div>
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
              <a class="st-settings__item" data-toggle="modal" data-target="#ask">
                <div class="st-settings__row">
                  <div class="st-settings__label">
                    문의하기
                  </div>
                  <div class="st-settings__control text-nowrap"><i class="fa fa-angle-right st-settings__ico"></i></div>
                </div>
              </a>
              <a class="st-settings__item" href="${pageContext.request.contextPath}/forum/info.do">
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


<script>

  $(function(){
    var checkedInit = user.lockMode=='T' ? true : false;
    if(checkedInit){
      var html = '잠금 모드 <i class="fa fa-lock" aria-hidden="true"></i>';
      $("#lock-label").html(html)
    }else{
      var html = '잠금 해제 <i class="fa fa-unlock" aria-hidden="true"></i>';
      $("#lock-label").html(html)
    }
    $("#lock-check").prop("checked", checkedInit)
  })


  $("body").on("click", "#lock-check", function(){
    var checkedInit = user.lockMode=='T' ? true : false;

    var pass = prompt("잠금모드\n비밀번호를 입력하시오.");
    if(!pass){
      $("#lock-check").prop("checked", checkedInit)
      return;
    }

    var checked = Boolean($("#lock-check").prop("checked"))
    checked = checked ? 'T' : 'F';


      $.ajax({
        type : "POST",
        url : projectURL+"/login/lock.json",
        data : {
          memberNo : user.memberNo,
          id : user.id,
          pass : pass,
          lockMode : checked
        },
        dataType : "JSON",
        beforeSend : function(){
          $("#lock-check").prop("checked", checkedInit)
        },
        success : function (data){

          if(data.result){
            //성공

            user.lockMode = checked;
            var checkedNew = user.lockMode=='T' ? true : false;
            $("#lock-check").prop("checked", checkedNew)
            if(checkedNew){
              var html = '잠금 모드 <i class="fa fa-lock" aria-hidden="true"></i>';
              $("#lock-label").html(html)
            }else{
              var html = '잠금 해제 <i class="fa fa-unlock" aria-hidden="true"></i>';
              $("#lock-label").html(html)
            }

          }else{

            //실패
            $("#lock-check").prop("checked", checkedInit)
            alert("비밀번호가 일치하지 않습니다. 보안시스템 작동");

          }
      },
      error : function(){
        $("#lock-check").prop("checked", checkedInit)
      }
    })


  })

</script>


<%@ include file="/WEB-INF/jsp/include/modal/notification-modal.jsp" %>
<script src="${pageContext.request.contextPath}/js/notification/notification.js"></script>
