
function Sticky() {
	this.note = null;
	this.dragging = false; // 현재 드래그중인지 여부 표시
	this.selected = false; // 현재 마우스다운 상태인지 표시
	this.diff = null; // 마우스 위치와 객체 위치
	this.x = null;  // 스티커 엘리먼트 left 좌표
	this.y = null;  // 스티커 엘리먼트 top 좌표
}

Sticky.prototype.createSticky = function (sticky) {
	
	var obj = this;
	
	var container = $("#stickyContainer");
	var note = $("<div></div>").attr("class", "stickyNote")
	                           .attr("contenteditable", "true");
	    
		note.on('mousedown', function() {
			return obj.doMouseDown.apply(obj, arguments);
		});
		
		note.on('mousemove', function() {
			return obj.doMouseMove.apply(obj, arguments);
		}); 
		
		note.on('mouseup', function() {
			return obj.doMouseUp.apply(obj, arguments);
		}); 
		
		note.on('keyup', function() {
			return obj.doKeyUp.apply(obj, arguments);
		}); 
	
	// 데이터베이스에서 자료를 가져온 경우	
	if (sticky) {
		note.attr("id", sticky.attr("id"));
		note.css({
			"position": "absolute",
			"left": sticky.attr("posX") + "px",
			"top": sticky.attr("posY") + "px"
		});
		note.html(sticky.attr("content"));
	}	
		
	container.append(note);	

	var loc = note.offset();
	this.x = loc.left; 
	this.y = loc.top;
	
	this.note = note;
	
	// 화면에서 메모 추가 버튼을 최초 누른 경우
	if (!sticky) {
		// 데이터 저장 
		this.save();
	}
};

Sticky.prototype.doMouseDown = function (event) {
	
	console.log(event);
	// 왼쪽 마우스   : 1 - 0
	// 오른쪽 마우스 : 3 - 2
	console.log(event.which, event.button, event.type);
	
	var isLeft = event.which == 1 && event.button == 0;
	if (!isLeft) return;
	
	this.selected = true;
	
	console.log("elX", this.x, "elY", this.y);
	
	var mouseX = event.clientX;
	var mouseY = event.clientY;
	
	var dd = document.documentElement;
	mouseX += dd.scrollLeft;
	mouseY += dd.scrollTop;

	// DIV note와 마우스 클릭과의 거리 차
	this.diff = {
		x: mouseX - this.x,
		y: mouseY - this.y
	};
};

Sticky.prototype.doMouseMove = function (event) {
	if (!this.selected) return false;
	
	if (!this.dragging) {
		this.dragging = true;
		this.note.css("opacity", "0.5");
	}

	this.note.css({
		"position": "absolute",
		"left": (event.clientX - this.diff.x) + "px",
		"top": (event.clientY - this.diff.y) + "px"
	});
};

Sticky.prototype.doMouseUp = function (event) {
	if (!this.selected) return;
	
	var loc = this.note.offset();
	this.x = loc.left; 
	this.y = loc.top;
	
	this.selected = false;
	this.diff = null;
	
	if (this.dragging) {
		this.dragging = false;
		this.note.css("opacity", "1.0");
	}
	
	this.edit();
};

// 키보드 키가 올라갈때 스티커의 내용을 저장함
Sticky.prototype.doKeyUp = function (event) {
	this.edit();
};

// 최초 메모 생성시 데이터 저장
Sticky.prototype.save = function (event) {
	var note = this.note;
	$.get(
		"/09_jquery/sticky/save.do",
		{x: this.x, y: this.y},
		function (data) {
			note.attr("id", data)
		}
	);
};

// 마우스가 올라갈때 좌표 수정, 키보드 글을 입력하거나 수정할 때
Sticky.prototype.edit = function (event) {
	var note = this.note;
	$.get(
		"/09_jquery/sticky/edit.do",
		{id: note.attr("id"), x: this.x, y: this.y, content: note.html()},
		function (data) {
		}
	);
};