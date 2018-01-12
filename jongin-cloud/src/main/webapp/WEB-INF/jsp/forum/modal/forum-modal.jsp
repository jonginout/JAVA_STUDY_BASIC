<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- Modal -->

<!-- dynatree -->
<link href="${pageContext.request.contextPath}/lib/dynatree-master/src/skin/ui.dynatree.css" rel="stylesheet" type="text/css" id="skinSheet">
<script src="${pageContext.request.contextPath}/lib/dynatree-master/src/jquery.dynatree.js"></script>

<style>
	.modal-header{
		padding : 10px 15px 10px 15px !important;
		margin: 0px;
	}
	.modal-header>h3,.modal-header>h4{
		margin: 0px;
	}
	.modal-footer{
		padding : 10px 15px 10px 15px !important;
	}
	#img-to-text-hidden{
		display: none;
	}
	#imgToText{
		width : 100%;
	}
	#imgTextCopy{
		height : 300px;
	}
	ul.dynatree-container{
		border: 1px solid white !important;
	}
	#categorys{
		margin: 10px;
		width : 94%;
	}
</style>

<div class="modal modal-middle fade scale-out" id="file-modal" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document" style="width: 94% !important">
		<div class="modal-content">
			<div class="modal-header">
				<h3>클라우드 파일 첨부하기</h3>
			</div>
			<div id="tree">
			</div>
			<div class="modal-footer">
				<button class="btn btn-default" id="attach-file-btn">첨부하기</button>			
				<button class="btn btn-default file-view-close" type="button" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<div class="modal modal-middle fade scale-out" id="select-category" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document" style="width: 94% !important">
		<div class="modal-content">
			<div class="modal-header">
				<h4><i class="fa fa-tag" aria-hidden="true"></i> 카테고리 선택</h4>
			</div>
			<div>
				<select class="form-control" id="categorys">
					<option value="자유">자유</option>
					<option value="질문">질문</option>
					<option value="유머">윾머</option>
					<option value="정치">정치/시사</option>
					<option value="홍보">홍보</option>
					<option value="취업">구인/구직</option>
				</select>
			</div>
			<div class="modal-footer">
				<button class="btn btn-default file-view-close" type="button" data-dismiss="modal">취소</button>
				<button class="btn btn-default" id="post-forum"><i class="fa fa-pencil" aria-hidden="true"></i> 글작성</button>			
			</div>
		</div>
	</div>
</div>


<script>

	var selectCloudFile;
	var attachCloudFile;

	$(".file-forum").click(function(){
		dirTree()
	})

	$("body").on("click", "#attach-file-btn",function(){
		if(!selectCloudFile){
			alert("파일을 선택하세요!");
			return;
		}
		if(selectCloudFile.isFolder){
			alert("파일을 선택하세요!");
			return;
		}
		$(".file-view-close").trigger("click")
		attachCloudFile = selectCloudFile;

		var text = '<i class="fa fa-times" aria-hidden="true"></i> 첨부파일 : '+selectCloudFile.title;
		$("#attach-file").html(text)
	})

	$("body").on("click", "div#attach-file", function(){
		if(confirm("첨부된 파일을 삭제하시겠습니까?")){
			attachCloudFile = null;
			$("#attach-file").html("")
		}
	})

// 트리 가져오기
function dirTree() {

	// 로그인 체크
	if(user==null){
		return false;
	}

	console.log("트리 로딩 완료..")

	$("#tree").dynatree({
	title: user.name+"님의 클라우드",
	fx: { height: "toggle", duration: 200 },
	autoFocus: false,
	initAjax: {
		url: projectURL+"/cloud/list.json",
		data : {user:user.memberId},
		},
	onActivate: function(node) {
		selectCloudFile = node.data
		console.log(selectCloudFile)
		},
	onLazyRead: function(node){
		node.appendAjax({
		url: projectURL+"/cloud/sublist.json",
		data : { path : node.data.path },
		debugLazyDelay: 200,
		success : function (node) {
			console.log(node)
			}
		});
	},
	// onClick : function (node) {
	// },
	minExpandLevel: 2,
	debugLevel: 0
	});


}

</script>