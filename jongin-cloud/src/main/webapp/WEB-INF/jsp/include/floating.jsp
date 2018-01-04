<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<style>
    .btn-group-sm .btn-fab{
        position: fixed !important;
        right: 26px;
        font-size: 17px;
    }
    .btn-group .btn-fab{
        position: fixed !important;
        right: 20px;
    }
    #float-btn{
        background: #1678ab;
        font-size: 28px;
        bottom: 20px;
        border-radius: 31px !important;
        width: 51px;
        height: 51px;
        line-height: normal;
    }
    .btn-fab{
        width: 40px;
        height: 40px;
        border-radius: 21px !important;
    }
    #scroll-top{
        bottom: 90px;
    }
    #logout{
        bottom: 145px;
    }
    #autre{
        bottom: 200px;
    }
    #link{
        bottom: 255px;
    }
    .float-btn-content{
        color: white;
        margin: auto !important;
    }
    #mini-fab{
        display: none;
    }
    #floating-btn{
        position: fixed;
        z-index: 999;
    }
</style>

<div id="floating-btn">
    <div class="btn-group-sm" id="mini-fab">
        <a class="btn btn-warning btn-fab" id="link">
            <span class="float-btn-content">
                <i class="fa fa-commenting-o" aria-hidden="true"></i>
            </span>
        </a>
        <a href="${pageContext.request.contextPath}/" class="btn btn-info btn-fab" id="autre">
            <span class="float-btn-content">
                <i class="fa fa-home" aria-hidden="true"></i>
            </span>
        </a>
        <a href="${pageContext.request.contextPath}/login/logout.do" class="btn btn-success btn-fab" id="logout">
            <span class="float-btn-content">
                <i class="fa fa-sign-out" aria-hidden="true"></i>
            </span>

        </a>
        <a class="btn btn-danger btn-fab" id="scroll-top">
            <span class="float-btn-content">
                <i class="fa fa-angle-double-up" aria-hidden="true"></i>
            </span>
        </a>
    </div>
    <div class="btn-group">
        <a class="btn btn-fab" id="float-btn" >
            <span class="float-btn-content"><i class="fa fa-bars" aria-hidden="true"></i></span>
        </a> 
    </div>
</div>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

<script>
    $("#float-btn").click(function() {
      $("#mini-fab").toggle(300);
    });

    $("#scroll-top").click(function(){
        var body = $('html, body');
        body.animate({scrollTop:0}, 500, 'swing');
    })


    $("#link").click(function(){



        //<![CDATA[
    // // 사용할 앱의 JavaScript 키를 설정해 주세요.
    Kakao.init('d97a3b08e5637c89bd3b046a6e13e268');
    // // 카카오링크 버튼을 생성합니다. 처음 한번만 호출하면 됩니다.
    Kakao.Link.createDefaultButton({
      container: '#link',
      objectType: 'feed',
      content: {
        title: '딸기 치즈 케익',
        description: '#케익 #딸기 #삼평동 #카페 #분위기 #소개팅',
        imageUrl: 'http://mud-kage.kakao.co.kr/dn/Q2iNx/btqgeRgV54P/VLdBs9cvyn8BJXB3o7N8UK/kakaolink40_original.png',
        link: {
          mobileWebUrl: 'https://developers.kakao.com',
          webUrl: 'https://developers.kakao.com'
        }
      },
      social: {
        likeCount: 286,
        commentCount: 45,
        sharedCount: 845
      },
      buttons: [
        {
          title: '웹으로 보기',
          link: {
            mobileWebUrl: 'https://developers.kakao.com',
            webUrl: 'https://developers.kakao.com'
          }
        },
        {
          title: '앱으로 보기',
          link: {
            mobileWebUrl: 'https://developers.kakao.com',
            webUrl: 'https://developers.kakao.com'
          }
        }
      ]
    });
  //]]>
    })

</script>