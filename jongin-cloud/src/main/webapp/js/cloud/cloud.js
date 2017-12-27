



// 확장자 체크
function fileExtChecker(ext){

	var chk = "";

	if(imgFileArr.indexOf(ext)!=-1){
		chk = "img"
	}else if(codeFileArr.indexOf(ext)!=-1){
		chk = "code"
	}else if(ext=='pdf'){
		chk = "pdf"
	}else if(audioFileArr.indexOf(ext)!=-1){
		chk = "audio"
	}else if(movieFileArr.indexOf(ext)!=-1){
		chk = "movie"
	}else if(zipFileArr.indexOf(ext)!=-1){
		chk = "zip"
	}else{
		chk = "ect"
	}

	return chk;
}

// 코드 뷰 사용가능 확장자
var codeFileArr = ["java","js","html","css","jsp","php","txt"];
// 이미지 뷰 사용가능 확장자
var imgFileArr = ["png","jpg","jpeg","gif","png","bmp"];
// 음악 사용가능 확장자
var audioFileArr = ["mp3", "ogg", "wav", "ACC"];
// 동영상파일 사용가능 확장자
var movieFileArr = ["mp4", "avi", "mkv", "wmv"];
// 압축파일 사용가능 확장자
var zipFileArr = ["zip", "alz", "egg"];

var rootNode = {};

// 현재 active된 경로
var nowNode = "";
// 리네임
var rename = "";

var editor;

var nowVoluemSize = 0;


// 왼쪽 파일 폴더 리스트
function showChildList(node) {
	var activeNode = node;
	
	if(node.data.isFolder){
		$("#detailFile").html("");
	}else {
		node = node.parent;
	}
	
	var childs = node.childList;
			
	var html = "<div class='alert alert-danger alert-dismissible' role='alert'>\
					<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>\
					<strong>폴더에</strong> 파일을 드래그 앤 드롭으로 업로드 가능!\
				</div>\
				<p class='h2 file' data-key='"+node.data.key+"'>\
					<i class='fa fa-folder-open' aria-hidden='true'></i>\
					"+node.data.title+"\
				</p>";
	
	
	childs && (childs = childs.length < 1 ? null : childs);
	if(childs){	
		for ( var key in childs) {
	    	 if(!childs[key].data.isFolder){
				var icon = '<i class="fa fa-file-o" aria-hidden="true"></i>';
				var extNow = childs[key].data.ext.toLowerCase();

				var fc = fileExtChecker(extNow);

				if(fc=='img'){
					icon = '<i class="fa fa-file-image-o" aria-hidden="true"></i>';
				}else if(fc=='code'){
					icon = '<i class="fa fa-file-code-o" aria-hidden="true"></i>';
				}else if(fc=='pdf'){
					icon = '<i class="fa fa-file-pdf-o" aria-hidden="true"></i>';
				}else if(fc=='audio'){
					icon = '<i class="fa fa-file-audio-o" aria-hidden="true"></i>';
				}else if(fc=='movie'){
					icon = '<i class="fa fa-file-video-o" aria-hidden="true"></i>';
				}else if(fc=='zip'){
					icon = '<i class="fa fa-file-archive-o" aria-hidden="true"></i>';
				}

				html += "<h3 class='file' data-key='"+childs[key].data.key+"'>\
							"+icon+" "+childs[key].data.title+"\
						</h3>";
		      }else {
				html += "<h3 class='file' data-key='"+childs[key].data.key+"'>\
							<i class='fa fa-folder-o' aria-hidden='true'></i> \
							"+childs[key].data.title+"\
						</h3>";
			}
		}
	}else {
		html += "<h3><i class='fa fa-exclamation-circle' aria-hidden='true'></i> 파일이 없습니다.</h3>";
	}
	$("#nowList").html(html);
	nowNode = activeNode.data;
	console.log("활성화된 노드 nowNode",nowNode)
}


// 트리 가져오기
function dirTree() {

	// 로그인 체크
	if(user==null){
		return false;
	}

	console.log("트리 로딩 완료..")

    $("#tree").dynatree({
      title: "김종인님의 클라우드",
      fx: { height: "toggle", duration: 200 },
      autoFocus: false,
      initAjax: {
        url: projectURL+"/cloud/list.json",
		data : {user:user.memberId},
        },
		onPostInit: function(isReloading, isError) {
			//용량 체크!
			rootNode = $("#tree").dynatree("getTree").getNodeByKey("_2").data
			volumeCheck($("#tree").dynatree("getTree").getNodeByKey("_2").data.size);
		},
      onActivate: function(node) {
    	console.log(node)
    	showChildList(node);
    	showFileDetail(node.data.key);
		focusFile($(".file[data-key="+node.data.key+"]"));
    	},
      onLazyRead: function(node){
        node.appendAjax({
          url: projectURL+"/cloud/sublist.json",
          data : { path : node.data.path },
		  debugLazyDelay: 200,
          success : function (node) {
        	  console.log(node)
        	  showChildList(node)
        	  showFileDetail(node.data.key)
        	  focusFile($(".file[data-key="+node.data.key+"]"));
			  
        	  // /////////////////////////파일명수정
        	  if(node.childList){	        		  
	        	  for (let ch of node.childList) {
	        		  	var ext = "";
	        		  	if(ch.data.ext){
	        		  		ext = "."+ch.data.ext;
	        		  	}
						if(ch.data.title==rename+ext){
							showFileDetail(ch.data.key)
							focusFile($(".file[data-key="+ch.data.key+"]"));
						}
				  }
	        	  rename = "";

        	  }
        	  // //////////////////////////
        	  
			}
        });
      },
      
      // //////////////드래그앤드롭////////////////드래그앤드롭
      dnd: {
          onDragStart: function(node) {
			console.log("onDragStart")
            return true;
          },
          autoExpandMS: 1000,
          preventVoidMoves: true,
          onDragEnter: function(node, sourceNode) {
			console.log("onDragEnter")
            return true;
          },
          onDragOver: function(node, sourceNode, hitMode) {

			console.log("onDragOver")
            // 옮기는 과정에서 위에 가져다 대기만 한 상황
            
            if(node.isDescendantOf(sourceNode)){
              return false;
            }
            // Prohibit creating childs in non-folders (only sorting
			// allowed)
            if( !node.data.isFolder && hitMode === "over" ){
              return "after";
            }
          },
          onDrop: function(node, sourceNode, hitMode, ui, draggable) {

			console.log("onDrop")
            // 노드 실게 그곳에 두어서 옮기기
            
            // 힛모드 이동인지 체크
            var hitModeChk = false;
            
            if(hitMode=="before" || hitMode=="after"){
            	if(node.data.title==userId){
            		alert("루트 폴더 밖으로는 옮길 수 없습니다.")
            		return false;
            	}
            	hitModeChk = true;
// node.data.path = node.data.path.substring(0,
// node.data.path.lastIndexOf("\\"));
            }
            
            // 노드 (겉모습만 이동)
            sourceNode.move(node, hitMode);
            
            console.log("옮길 것",sourceNode.data.path);
            console.log("옮겨질 곳",node.data.path);

            moveFile(sourceNode.data, node.data, hitModeChk)
            
          }
        },
		// //////////////드래그앤드롭////////////////드래그앤드롭
		onKeydown: function(node, event) {
			switch( event.which ) {
				case 113: // [F2]
					$(".file-rename").trigger("click")
					return false;
				case 46: // [Del]
				$(".file-delete").trigger("click")
					return false;			
			}
		},
      
// onClick : function (node) {
// },
      minExpandLevel: 2,
      debugLevel: 0
	});
	

}


// 파일 드래그 앤 드롭 이동
function moveFile(moveNode, recNode, hitModeChk) {
	
	// hitMode 였을때는 path를 부모경로로 잠시 바꿔준다.
	var recPath = recNode.path;
	if(hitModeChk){
		recPath = recNode.path.substring(0, recNode.path.lastIndexOf("\\"));
	}
	
	$.ajax({
		type : "POST",
		url : projectURL+"/cloud/filemove.json",
		data : {
			moveFilePath : moveNode.path,
			recFilePath : recPath
		},
		success : function (data) {
			if(data.result){
				var node =  $("#tree").dynatree("getTree").getNodeByKey(moveNode.key);
				// path 값을 한번 초기화
				node.data.path = data.path;
				
				// 옮겨진 곳을 한번 리로드
				lazyReloadTarget(recNode.key)
			}else {
				alert("파일이동 실패!!");					
			}
		}
	})
}

// 트리 불러오기
$(function(){
	dirTree();
});