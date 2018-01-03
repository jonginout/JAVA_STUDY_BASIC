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
    #mail{
        bottom: 90px;
    }
    #sms{
        bottom: 145px;
    }
    #autre{
        bottom: 200px;
    }
    .float-btn-content{
        color: white;
        margin: auto !important;
    }
</style>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
        <div class="btn-group-sm hidden" id="mini-fab">
            <a href="#" class="btn btn-info btn-fab" data-toggle="tooltip" data-placement="left" data-original-title="Other" title="" id="autre">
                <span class="float-btn-content">ss</span>
            </a>
            <a href="#" class="btn btn-warning btn-fab" data-toggle="tooltip" data-placement="left" data-original-title="SMS" title="" id="sms">
                <span class="float-btn-content">ss</span>

            </a>
            <a href="#" class="btn btn-danger btn-fab" data-toggle="tooltip" data-placement="left" data-original-title="Mail" title="" id="mail">
                <span class="float-btn-content">ss</span>
            </a>
        </div>
        <div class="btn-group">
            <a href="javascript:void(0)" class="btn btn-fab" id="float-btn" >
                <span class="float-btn-content"><i class="fa fa-bars" aria-hidden="true"></i></span>
            </a>
        </div>
        </div>
    </div>
</div>

<script>
    $("#float-btn").click(function() {
      $("#mini-fab").toggleClass('hidden');
    });

    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip({
            delay : 100
          })
    })
</script>