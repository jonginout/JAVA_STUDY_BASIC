


$(function(){

    function hideShowAllReadBtn(){
      var chkAlarmCount = parseInt($(".st-notifications__count").text().trim())
      console.log(chkAlarmCount)
      if(chkAlarmCount<1){
        $(".st-notifications__all").hide()
      }else{
        $(".st-notifications__all").show()    
      }
    }
  
    // 알람 안읽은 개수 로딩
    $.ajax({
      type : "GET",
      url : projectURL+"/alarm/countalarm.json",
      success : function (data){
        hideShowAllReadBtn()
        $(".st-notifications__count").html(data)
        $(".st-header__count.st-notifications-count").html('<strong>'+data+'</strong>')
      }
    })
  
    // 모두 읽기
    $("body").on("click", ".st-notifications__all", function(){
  
        $.ajax({
            type : "GET",
            url : projectURL+"/alarm/readallalarm.json",
            success : function (){
              $(".st-notification.new").removeClass("new")
              $(".st-notifications__count").html("0")
              $(".st-header__count.st-notifications-count").html('<strong>0</strong>')
              $(".st-notifications__all").hide()
          }
        })
    })
  
    // 알람 삭제
    $("body").on("click", ".st-notification__mark", function(){
      var parent = $(this).parents(".st-notification");
      var no = parent.attr("data-alarmNo");
  
      loadingAjax("삭제중..", parent)
      $.ajax({
              type : "GET",
              url : projectURL+"/alarm/deletealarm.json",
              data : { alarmNo : no },
              success : function(data){   
          parent.hide(100);
          loadingStopAjax(parent)
        },
        error : function(){
          loadingStopAjax(parent)
        }
      })
    })
  
    // 알람 읽기
  function readAlarm(parents){
      var no = parseInt(parents.attr("data-alarmNo").trim()); 
      var ele = parents
      loadingAjax("이동중..", ele)
  
      $.ajax({
              type : "GET",
              url : projectURL+"/alarm/readalarm.json",
              data : { alarmNo : no },
              success : function(data){   
          loadingStopAjax(ele)
          ele.removeClass("new")
          ele.find(".st-notification__mark").remove()
  
          var AlarmCnt = (parseInt($(".st-notifications__count").text().trim()))-1;
  
          $(".st-notifications__count").html(AlarmCnt)
          $(".st-header__count.st-notifications-count").html('<strong>'+AlarmCnt+'</strong>')
          hideShowAllReadBtn()
  
          goLingAlarm(parents)
        },
        error : function() {
          loadingStopAjax(ele)
        }
      })
  }
  
    $("body").on("click", ".st-notification p,.st-notification b", function(){
      var parents = $(this).parents(".st-notification");
      if(parents.hasClass("new")){
        readAlarm(parents)
      }else{
        goLingAlarm(parents)
      }
    })
      
    // 알람 링크가기
    function goLingAlarm(ele){
      var forumNo = parseInt(ele.attr("data-forumNo"))
      var commentNo = parseInt(ele.attr("data-commentNo"))
      location.href = projectURL+"/forum/forum.do?forumNo="+forumNo+"&commentNo="+commentNo
    }
  
    // 알람 실시간 로딩
    $("body").on("click", ".header-alarm-btn", function(){
      $(".alarm-list").html("");
      loadAlarmList()
      hideShowAllReadBtn()
    })
  
    function loadAlarmList(){
      
        var ele = $(".st-notifications");
        loadingAjax("실시간 알람 가져오는중..", ele)
    
        $.ajax({
          type : "GET",
          url : projectURL+"/alarm/alarm.json",
          data : {
            receiver : user.memberNo
          },
          success : function(data){ 
            console.log(data)     
            $(".alarm-list").html("");
  
            moment.locale('ko');
    
            if(data.result){
              for(var alarm of data.alarms){
    
                var isRead = "";
                if(alarm.isRead=="F"){
                  isRead = " new";
                } 
    
                var momentTime = moment(alarm.regDate).fromNow();
                var html = '<div class="st-notification'+isRead+'" data-alarmNo="'+alarm.alarmNo+'" data-forumNo="'+alarm.forumNo+'" data-commentNo="'+alarm.commentNo+'">\
                              <div class="st-notification__main">\
                                <div class="st-notification__title"><b>댓글알람</b><small>'+momentTime+'</small>\
                                  <div class="st-notification__mark"><i class="fa fa-times fa-lg"></i></div>\
                                </div>\
                                <p>'+alarm.content+'</p>\
                              </div>\
                            </div>';
    
                $(".alarm-list").append(html);
              }
            }
            loadingStopAjax(ele)
          },
          error: function() {
            loadingStopAjax(ele)
          }
        })
      }
  
    })