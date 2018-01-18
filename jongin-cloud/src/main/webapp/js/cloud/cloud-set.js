//클라우드 제어 js

// 클립보드
$(function(){
	$(document).ready(function(){
		var clipboard = new Clipboard('.clipboard');
	});
})

// 프로그레스바 추가
function volumeCheck(size) {
	//프로그레스바
	var maxSize = parseInt($(".progress-bar").attr("aria-valuemax"));
	var nowSize = Math.round((parseInt(size))/1024/1024)
	var x = (100/maxSize);
	var percent = Math.round(nowSize*x);

	$(".progress-bar").attr("aria-valuenow", nowSize)
					  .attr("aria-valuemax", maxSize)
					  .css("width", percent+"%")
	$("#percent").html(percent+"% 사용중")

	if(percent>=50 && percent<90){
		$(".progress-bar").removeClass("progress-bar-success")
						  .removeClass("progress-bar-danger")
						  .addClass("progress-bar-warning")
	}else if(percent>=90){
		$(".progress-bar").removeClass("progress-bar-warning")
						  .removeClass("progress-bar-success")
						  .addClass("progress-bar-danger")
	}else{
		$(".progress-bar").removeClass("progress-bar-warning")
						  .removeClass("progress-bar-danger")
						  .addClass("progress-bar-success")
	}
};

// 파일 선택시 빨강 포커스
function focusFile(ele) {
	$(".file").removeClass("selected");
	ele.addClass("selected");
}

// 파일 선택시 빨강 포커스
$("body").on("click", ".file", function () {
	focusFile($(this));
	var key = $(this).attr("data-key");
	var node = $("#tree").dynatree("getTree").getNodeByKey(key);
	node.activate(); // 강제 엑티브
	node.expand(); // 강제 오픈
	showFileDetail(key);
})

// 전체 새로고침
$("#reload").click(function () {
	nowNode = null;
	$("#nowList").html("");
	$("#detailFile").html("");
	$("#tree").dynatree("destroy");
	dirTree();
})

// 새폴더
$("#newFolder").click(function () {
	if(!nowNode || !nowNode.isFolder){
		swal("Error", "폴더를 선택하지 않았거나,\n선택하신 폴더가 없습니다.", "error")
		return false;
	}
	var newFolderName = prompt("새폴더 이름을 작성하세요.");
	if(!newFolderName){
		return false;
	}
	$.ajax({
		type : "POST",
		url : projectURL+"/cloud/newfolder.json",
		data : {
			path : nowNode.path,
			name : newFolderName
		},
		dataType : "json",
		success : function (data) {
			if(data.dup){
				swal("Error", "똑같은 이름의 폴더가 존재합니다.", "error")
				return false;
			}
			$("#nowList").html("");
			lazyReloadActive();
		}
	})
})

		
// 새코드
$("#newCode").click(function () {
	if(!nowNode || !nowNode.isFolder){
		swal("Error", "폴더를 선택하지 않았거나,\n선택하신 폴더가 없습니다.", "error")
		return false;
	}
	var newCodeFile = prompt("새 코드파일 이름을 작성하세요.(확장자 까지 포함)\n-권장 파일 형식 [java, js, html, css, jsp, php, txt]");
	if(!newCodeFile){
		return false;
	}else{
		var newExt = newCodeFile.substring(newCodeFile.lastIndexOf(".")+1);
	}
	$.ajax({
		type : "POST",
		url : projectURL+"/cloud/newCode.json",
		data : {
			path : nowNode.path,
			name : newCodeFile
		},
		dataType : "json",
		success : function (data) {
			if(data.dup){
			swal("Error", "똑같은 이름의 파일이 존재합니다.", "error")
				return false;
			}
			$("#nowList").html("");
			lazyReloadActive();
		}
	})
})

// 액티브된 노드 레이지 리로드
function lazyReloadActive() {
	var node = $("#tree").dynatree("getActiveNode");
	if (node && node.isLazy()) {
		node.reloadChildren(function(node, isOk) {
		});
	} else {
		swal("Error", "Please activate a lazy node first.", "error")
	}
	
};

// 특정 노드를 리로드
function lazyReloadTarget(key) {
	var node =  $("#tree").dynatree("getTree").getNodeByKey(key);
	if (node && node.isLazy()) {
		node.reloadChildren(function(node, isOk) {
		});
	} else {
		swal("Error", "Please activate a lazy node first.", "error")
	}
};

// 우측 디테일 부분
function showFileDetail(key) {
	$("#detailFile").attr({"data-key":key})
	
	var selectNode = $("#tree").dynatree("getTree").getNodeByKey(key).data;
	
	var html = "";
	
	if(selectNode.title!=user.id){
		html = '<div class="btn-group btn-group-justified">\
					<div class="btn-group file-delete">\
						<button type="button" class="btn btn-default">\
							<i class="fa fa-trash" aria-hidden="true"></i> 삭제\
						</button>\
					</div>\
					<div class="btn-group file-rename">\
						<button type="button" class="btn btn-default">\
							<i class="fa fa-pencil-square-o" aria-hidden="true"></i> 이름 변경\
						</button>\
					</div>\
					<div class="btn-group file-down">\
						<button type="button" class="btn btn-default">\
							<i class="fa fa-download" aria-hidden="true"></i> 다운로드\
						</button>\
					</div>\
				</div>';
	};

	// 파일명
	html += "<h1>\
				<span id='fileName'>"+selectNode.title+"</span>\
			</h1>\
				<div id='updateDate'>수정한 날짜 : <span id='updateDate'>"+dateFormat(selectNode.updateDate)+"</span></div>";
	
	// /확장자 구분
	
	// 뷰어 버튼
	if(!selectNode.isFolder){

		var ext = selectNode.ext.toLowerCase();
		var fc = fileExtChecker(ext);

		if(fc=='code'){		
			html += `
				<div class="codeBtn-box">
					<button type="button" class="code-view btn btn-default btn-sm">
						<i class="fa fa-file-code-o" aria-hidden="true"></i> 코드 보기
					</button>
					<button type="button" class="code-refresh btn btn-default btn-sm">
						<i class="fa fa-refresh" aria-hidden="true"></i> 코드 새로고침
					</button>
				</div>
				<div class="code-content">
				</div>
				`;
		}else if(fc=='img' || fc == 'pdf' || fc == 'audio' || fc == 'movie'){

			var icon = {
				'img' : '<i class="fa fa-file-image-o" aria-hidden="true"></i> 이미지 보기',
				'pdf' : '<i class="fa fa-file-pdf-o" aria-hidden="true"></i> PDF 보기',
				'audio' : '<i class="fa fa-file-audio-o" aria-hidden="true"></i> 오디오 듣기',
				'movie' : '<i class="fa fa-file-video-o" aria-hidden="true"></i> 동영상 재생',
			}

			html += '<div class="imageBtn-box">\
						<button type="button" class="file-view-btn btn btn-default btn-sm" data-toggle="modal" data-target="#file-modal">\
							'+icon[fc]+'\
						</button>\
					</div>'; 
		}
	}

	if(selectNode.title!=user.id){
		html += `<br>
		<form id="form-comment">
			<div class="form-group" style="margin-bottom:-1px;">
				<label for="comment-file"><i class="fa fa-comments-o" aria-hidden="true"></i> 이 파일에 대한 코멘트</label>
				<textarea style="overflow:visible;" name="comment" class="form-control" id="comment-file"></textarea>
			</div>
			<button type="button" class="btn btn-block btn btn-danger btn-sm comment-btn">
				<i class="fa fa-comments-o" aria-hidden="true"></i> 
				코멘트 수정
			</button>
		</form>
		`;
	}else{
		html += '<br><h1>Root 디렉토리</h1>'
	}

					
	if(!selectNode.isFolder){
		
		html += `<br><div class="input-group">
					<input id="copy" type="text" class="form-control" readonly>
					<span class="input-group-btn">
					<button class="btn btn-success make-down-link">
						<i class="fa fa-link" aria-hidden="true"></i>
						다운링크 생성
					</button>					
					<button class="btn btn-success clipboard clipboard-btn" type="button" data-clipboard-target="#copy" style="display:none">
						<i class="fa fa-clone" aria-hidden="true"></i> 
						다운링크 복사
					</button>
					</span>
				</div>`;
	}
	
	$("#detailFile").html(html);

	$.ajax({
		type : "POST",
		url : projectURL+"/cloud/commentview.json",
		data : {
			title : selectNode.title,
			path : selectNode.path.substring(0,(selectNode.path.lastIndexOf(selectNode.title))-1)
		},
		success : function(data){
			$("#comment-file").val(data);
			$("#comment-file").height(1).height( $("#comment-file").prop('scrollHeight')+12 );
		}
	})

}

// 닫기 버튼
$("body").on("click", ".file-view-close", function(){	
	$(".file-view-content").html("")
})

 

// 코멘트 저장하기
$("body").on("click", "#form-comment>button", function(){

	if($("#comment-file").val().length<3){
		swal("Error", "코멘트를 3자 이상 입력하세요!", "error")
		return false;
	}

	console.log(nowNode.title)
	var comment = $("#comment-file").val();
	$.ajax({
		type : "POST",
		url : projectURL+"/cloud/filecomment.json",
		data : {
			comment: comment,
			title : nowNode.title,
			path : nowNode.path.substring(0,(nowNode.path.lastIndexOf(nowNode.title))-1)
		},
		success : function(){
			swal("Success", "코멘트 저장 완료", "success")
		},
		error : function(){
			swal("Error", "코멘트 저장 완료", "error")
		}
	})
})

// 파일 삭제
$("body").on("click", ".file-delete", function () {

	////////////////////////
	if(user.lockMode=='T'){
		swal("※경고※", "잠금 모드가 활성화 중입니다.\n잠금 모드 일 경우 파일 삭제, 수정 불가", "error")
		return;
	}
	////////////////////////

	swal({
		title: "Deleted",
		text: "이 파일을 삭제하시겠습니까?\n※ 추후 복구불가",
		type: "warning",
		showCancelButton: true,
		closeOnConfirm: false
	  },
	  function(isConfirm) {
		if (isConfirm) {
			deleteFile()
		}else{
			return false;
		}
	  });


	// if(!confirm("정말로 이 파일을 삭제하시겠습니까?\n※ 추후 복구불가")){
	// 	return false;
	// }
	
	var key = $(this).parents("#detailFile").attr("data-key");
	var parentKey = $("#tree").dynatree("getTree").getNodeByKey(key).parent.data.key;
	var path = $("#tree").dynatree("getTree").getNodeByKey(key).data.path;
	var nowSize = $("#tree").dynatree("getActiveNode").data.size;
	
	function deleteFile(){
		$.ajax({
			type : "POST",
			url : projectURL+"/cloud/filedelete.json",
			data : {path:path},
			success : function () {
				swal("Success", "삭제 성공", "success")
				lazyReloadTarget(parentKey);
				showFileDetail(parentKey)
	
				//용량 삭제
				rootNode.size = rootNode.size - nowSize;
				volumeCheck(rootNode.size)
			},
			error : function () {
				swal("Error", "삭제 실패", "error")
			}
		})
	}
})

// 파일 리네임
$("body").on("click", ".file-rename", function () {

	////////////////////////
	if(user.lockMode=='T'){
		swal("※경고※", "잠금 모드가 활성화 중입니다.\n잠금 모드 일 경우 파일 삭제, 수정 불가", "error")
		return;
	}
	////////////////////////

	var key = $(this).parents("#detailFile").attr("data-key");
	var node = $("#tree").dynatree("getTree").getNodeByKey(key);
	var parentKey = node.parent.data.key;
	var path = node.data.path;
	var title = node.data.title;
	var ext = node.data.ext;
	var isFolder = node.data.isFolder;
	
	if(isFolder){
		rename = prompt("변경할 이름을 작성하세요. (확장자 제외)", title);
	}else {				
		rename = prompt("변경할 이름을 작성하세요. (확장자 제외)", title.substring(0,title.lastIndexOf(".")));
	}
	if(!rename){
		return false;
	}

	$.ajax({
		type : "POST",
		url : projectURL+"/cloud/filerename.json",
		data : {
			path : path,
			rename : rename.trim(),
			nowHost : nowHost
			},
		success : function (data) {
			if(data.result){
				swal("Success", "수정 성공", "success")
				lazyReloadTarget(parentKey)
			}else {
				swal("Error", "수정 실패", "error")
			}
		}
	})
})

// 파일 다운로드 우측하단 버튼
$("body").on("click", ".file-down", function () {
	var key = $(this).parents("#detailFile").attr("data-key");
	var node = $("#tree").dynatree("getTree").getNodeByKey(key);

	var path = (node.data.path).replace(/\\/gi, "/");

	location.href = projectURL+"/common/down.do?ext="+node.data.ext+"&path="+path+"&title="+node.data.title;
})

// 상단 파일 첨부 버튼
$("#uploadForm>input[type=file]").click(function () {
	$("#uploadForm>input[name=uploadPath]").val("");
	if(!nowNode || !nowNode.isFolder){
		swal("Error", "폴더를 선택하지 않았거나,\n선택하신 폴더가 없습니다.", "error")
		return false;
	}
	$("#uploadForm>input[name=uploadPath]").val(nowNode.path)			
})

function fileSizeChk(fd){
	var size = 0;
	for(var pair of fd.entries()) {
	  if (pair[1] instanceof Blob) 
		size += pair[1].size;
	  else
		size += pair[1].length;
	}
	return size;
}

// 업로드 버튼
$("#uploadForm>button").click(function () {
	if(!$("#uploadForm>input[name=uploadPath]").val()){
		swal("Error", "파일을 선택하세요.", "error")
		return false;
	}
	var fd = new FormData($("#uploadForm")[0]);

	//용량체크
	if(rootNode.size + fileSizeChk(fd)>rootNode.maxSize){
		swal({
			title: "Max Cloud",
			text: "용량초과!! 용량추가 하시겠습니까?",
			type: "warning",
			showCancelButton: true,
			closeOnConfirm: false
		  },
		  function(isConfirm) {
			if (isConfirm) {
				swal.close()
				$("button[data-target='#addVolume']").trigger("click");
			}else{
				return false;
			}
		  });	
		// if(confirm("용량초과!! 용량추가 하시겠습니까?")){
		// 	$("button[data-target='#addVolume']").trigger("click");
		// }
		return;
	}
	
	
	$.ajax({
		type : "post",
		url : projectURL+"/cloud/fileupload.json",
		data : fd,
		processData : false,
		contentType : false,
		beforeSend : function(){
			loadingAjax("파일 업로드 중 ..")
		},
		success : function (data) {
			var msg = "파일 업로드 성공!";
			if(data.dup){
				msg = "파일 업로드 성공!\n※ 중복된 파일명은 자동 변경되어 업로드 되었습니다. ※"
			}
			swal("Success", msg, "success")
			$("#uploadForm")[0].reset();
			$("#uploadForm>input[name=uploadPath]").val("");
			lazyReloadActive();
			//용량 더하기
			rootNode.size = rootNode.size + fileSizeChk(fd);

			volumeCheck(rootNode.size)
			loadingStopAjax()
		},
		error : function () {
			swal("Error", "파일 업로드 실패!!", "error")
			loadingStopAjax()
		}
	})
	
})

// 코드 보기
$("body").on("click", ".code-view", function(){
	var key = $(this).parents("#detailFile").attr("data-key");
	var node = $("#tree").dynatree("getTree").getNodeByKey(key);

	$.ajax({
		type : "post",
		url : projectURL+"/cloud/codeview.json",
		data : {path : node.data.path},
		success : function (data) {

			var html ='<form class="CodeMirror">\
						<textarea id="code" name="code" class="code-editor"></textarea>\
					  </form>\
					  <p style="text-align: right">F11 : 전체화면</p>';
					  
			$(".code-content").html(html);

			loadEditor(data.code);
						
			var buttonHtml = '<button class="btn btn-default code-plus-btn" type="button" id="codeEidtBtn" data-key="'+key+'">\
								<i class="fa fa-floppy-o" aria-hidden="true"></i> 수정하기\
							</button> ';
			buttonHtml +='<button class="btn btn-default code-plus-btn" type="button" id="zoomCodeBtn" data-key="'+key+'">\
							   <i class="fa fa-search-plus" aria-hidden="true"></i> 확대\
						  </button> ';
			$(".code-plus-btn").remove();
			$(".codeBtn-box").append(buttonHtml);

		},
		error : function () {
			swal("Error", "코드 보기 실패!!", "error")
		}
	});
})

// 확대 하기
$("body").on("click", "#zoomCodeBtn", function(){
	editor.setOption("fullScreen", !editor.getOption("fullScreen"));
})
$("body").on("click", "#full-screen-close", function(){
	if (editor.getOption("fullScreen")) editor.setOption("fullScreen", false);
})


// 코드 에디터 로드하기
function loadEditor(code){

	$("#plzSave").remove();
	editor = codeLoad("code");
	editor.setValue(code)

	editor.on("change", function(){
		var plzSave = `
			<span id="plzSave" style='color:red'> 
				<i class='fa fa-dot-circle-o' aria-hidden='true'></i>
			</span>
		`;
		$("#fileName").html(nowNode.title+plzSave);
	})
}

// 코드 로드
function codeLoad(ele){
	return CodeMirror.fromTextArea(document.getElementById(ele), {
		lineNumbers: true,
		styleActiveLine: true,
		matchBrackets: true, 
		viewportMargin: Infinity,
		theme : "ambiance",
		extraKeys: {
			"F11": function(cm) {
				cm.setOption("fullScreen", !cm.getOption("fullScreen"));
			},
			"Esc": function(cm) {
				if (cm.getOption("fullScreen")) cm.setOption("fullScreen", false);
			}
		}	
	  });
}

// 코드 새로고침
$("body").on("click", ".code-refresh", function(){
	$(".code-view").trigger("click");
})


// 코드 수정하기
$("body").on("click", "#codeEidtBtn", function(){

	////////////////////////
	if(user.lockMode=='T'){
		swal("※경고※", "잠금 모드가 활성화 중입니다.\n잠금 모드 일 경우 파일 삭제, 수정 불가", "error")
		return;
	}
	////////////////////////

	var node = $("#tree").dynatree("getTree").getNodeByKey($(this).attr("data-key"));

	$.ajax({
		type : "post",
		url : projectURL+"/cloud/codechange.json",
		data : {
			changeCode : editor.getValue(),
			path : node.data.path
			},
		success : function(data){
			swal("Success", "코드 저장 성공!", "success")
			$(".code-view").trigger("click");
			$("#updateDate").html(dateFormat(Date.now()))
		},
		error : function(){
			swal("Error", "코드 수정 실패!!", "error")
		}
	})
	
})

// 이미지 PDF 동영상 음악 뷰어
$("body").on("click",".file-view-btn",function(){
	$("#textConvertBtn").html("")

	var key = $(this).parents("#detailFile").attr("data-key");
	var node = $("#tree").dynatree("getTree").getNodeByKey(key);

	var path = (node.data.path).replace(/\\/gi, "/");
	var ext = node.data.ext.toLowerCase();

	var viewUrl = projectURL+"/common/down.do?ext="+ext+"&path="+path;
	var downUrl = viewUrl+"&title="+node.data.title;

	var fc = fileExtChecker(ext);
	var wh100 = 'style="width:100%;height:100%"';

	if(fc == 'img'){
		console.log('?')
		$("#textConvertBtn").html('<button class="btn btn-default img-to-text-btn" type="button">텍스트 추출</button>')
	}

	var viweHtml = {
		'img' : '<img '+wh100+' src="'+viewUrl+'" alt="'+node.data.title+'" />',
		'pdf' : '<object data="'+viewUrl+'" type="application/pdf" width="100%" height="600px"></object>',
		'audio' : '<div '+wh100+'><audio controls><source src="'+viewUrl+'" type="audio/mpeg"></audio></div>',
		'movie' : '<video '+wh100+' controls><source src="'+viewUrl+'" type="video/mp4"></video>',
	}

	$(".file-view-content").html("").html(viweHtml[fc]);
	$("#file-down-link>a").attr("href", downUrl).html(node.data.title)

})


// 드래그 & 드롭으로 파일 업로드
$("body").on('dragover', '.file', function (e) {
		e.stopPropagation();
		e.preventDefault();
		// console.log(e);
		$(this).addClass("drag-over")
});

$("body").on('dragleave', '.file', function (e) {
		e.stopPropagation();
		e.preventDefault();
		// console.log(e);
		$(this).removeClass("drag-over")
});

$("body").on("drop", '.file', function (e){
	e.stopPropagation();
	e.preventDefault();
	// console.log(e);

	$(this).removeClass("drag-over")
	var key = $(this).attr("data-key");
	var node = $("#tree").dynatree("getTree").getNodeByKey(key);

	if(!node.data.isFolder){
		alert("폴더에만 파일을 업로드 할 수 있습니다.");
		return false;
	}

	console.log(node.data)

	var files = e.originalEvent.dataTransfer.files;
	if(files.length<1){
		return false;
	}else{
		var fd = new FormData();

		fd.append('uploadPath',node.data.path)
		for (var i = 0; i < files.length; i++) {
    		fd.append('files', files[i]);
		}

		//용량체크
		if(rootNode.size + fileSizeChk(fd)>rootNode.maxSize){
			swal({
				title: "Max Cloud",
				text: "용량초과!! 용량추가 하시겠습니까?",
				type: "warning",
				showCancelButton: true,
				closeOnConfirm: false
			  },
			  function(isConfirm) {
				if (isConfirm) {
					swal.close()
					$("button[data-target='#addVolume']").trigger("click");
				}else{
					return false;
				}
			  });
			return;
		}
		 
		$.ajax({
			type : "post",
			url : projectURL+"/cloud/fileupload.json",
			data : fd,
			processData : false,
			contentType : false,
			success : function (data) {
				var msg = "파일 업로드 성공!";
				if(data.dup){
					msg = "파일 업로드 성공!\n※ 중복된 파일명은 자동 변경되어 업로드 되었습니다. ※"
				}
				
				node.activate(); // 강제 엑티브
				node.expand(); // 강제 오픈
				focusFile($(this));
				showFileDetail(key);

				alert(msg);
				lazyReloadTarget(key);

				//용량 더하기
				rootNode.size = rootNode.size + fileSizeChk(fd);
				volumeCheck(rootNode.size)
			},
			error : function () {
				alert("파일 업로드 실패!!");
			}
		})
	}

});


function load() {
	gapi.client.setApiKey('AIzaSyChPkv0zZP46JBJhHiu2I9_2SoF6XDWcXQ');      
	gapi.client.load('urlshortener', 'v1',function(){});
}
$("body").on("click", ".make-down-link", function(){
	
	var path = (nowNode.path).replace(/\\/gi, "/");
	var ext = nowNode.ext.toLowerCase();
	var url = window.location.origin+"/"+projectURL+"/common/down.do?ext="+ext+"&path="+path+"&title="+nowNode.title;

	var longURL = url;    
		var request = gapi.client.urlshortener.url.insert({
			'resource' : {
				'longUrl' : longURL
			}
		});
		request.execute(function(response) {
			if (response.id != null) {
				$('#copy').val(response.id);
				$(".make-down-link").hide();
				$(".clipboard-btn").show();
			} else {
				alert("error: creating short url");
			}
		});

})



///

$("body").on('keydown keyup', '#comment-file', function () {
	$(this).height(1).height( $(this).prop('scrollHeight')+7 );
});
	