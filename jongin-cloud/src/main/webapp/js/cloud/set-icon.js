// 확장자 체크
function fileExtChecker(ext){

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

function setIcon(array){
	var iconFileDir = projectURL+"/lib/dynatree-master/icon/small/"

	for( var i in array ){

		if(array[i].data.isFolder){
			array[i].data.icon = iconFileDir+"folder.png";
			continue;
		}

		var ext = array[i].data.ext.toLowerCase();
		var fc = fileExtChecker(ext);
		var icon = iconFileDir;

		if(fc=='img'){
			icon += "file-img.png"			
		}else if(fc=='code'){
			icon += "file-code.png"
		}else if(fc=='pdf'){
			icon += "file-pdf.png"
		}else if(fc=='audio'){
			icon += "file-audio.png"
		}else if(fc=='movie'){
			icon += "file-movie.png"
		}else if(fc=='zip'){
			icon += "file-zip.png"
		}else if(ext=='docx' || ext=='docm' || ext=='doc' || ext=='dotx' || ext=='dotm' || ext=='dot'){
			icon += "file-word.png"
		}else if(ext=='xls' || ext=='xlsx' || ext=='csv'){
			icon += "file-xls.png"
		}else if(ext=='pptx' || ext=='ppt' || ext=='pptm'){
			icon += "file-ppt.png"
		}else{
			icon += "file-default.png"
		}
		
		array[i].data.icon = icon;
	}

	$("#tree").dynatree("getTree").renderInvisibleNodes()
}