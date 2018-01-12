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
    #reload{
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
        <a class="btn btn-warning btn-fab" id="reload">
            <span class="float-btn-content">
                <i class="fa fa-refresh" aria-hidden="true"></i>
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
    $("body").on("click", "#float-btn",function() {
      $("#mini-fab").toggle(300);
    });

    $("body").on("click", "#scroll-top",function() {
        var body = $('html, body');
        body.animate({scrollTop:0}, 500, 'swing');
    })

    $("body").on("click", "#reload",function() {
        location.reload()
    })

</script>