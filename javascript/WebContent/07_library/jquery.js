/**
 *  jquery 라이브러리 기능 구현해 보기..
 */
function jquery(val) {

	var elements = [];

	if(typeof (val) == "function"){
		window.onload = val;
		return;
	}

	//일반 엘리먼트 객체처리
	if(val instanceof HTMLElement){
		elements[0] = val;
	}else {
		switch (val.charAt(0)) {
			case "<":
			elements[0] = document.createElement(val.substring(1, val.length-1));
			break;
			default:
			for (let el of document.querySelectorAll(val)) {
				elements.push(el);
			}
		}
	}



	// html 이 존재하면 설정, 존재하지 않으면 첫번째 값을 반환
	elements.html = function (html) {
		if (html != undefined) {
			for (let el of this) {
				//this는 일반적으로 함수를 호출한 객체
                // 여기서는 elements
				el.innerHTML = html;
			}
			return this;
		}
		return this[0].innerHTML;
	};


	elements.text = function (text) {
		if (text != undefined) {
			for (var el of this) {
				el.innerText = text;
			}
			return this;
		}

		var result = this[0].innerText;
		for (var i = 1; i < this.length; i++) {
			result += "\n" + this[i].innerText;
		}
		return result;
	};

    elements.attr = function (name, value) {
		// 객체인 경우
		if (typeof (name) == "object") {
			for (let el of this) {
				for (let key in name) {
					el.setAttribute(key, name[key]);
				}
			}
			return this;
		}else {
			// 객체가 아닌 경우
			if (value != undefined) {
				for (let el of this) {
					el.setAttribute(name, value);
				}
				return this;
			}
			return this[0].getAttribute(name);
		}
	};


    elements.val = function (value) {
		if (value != undefined) {
			for (let el of this) {
				el.value = value;
			}
			return this;
		}
		return this[0].value;
	};

// $("#list")[0].appendChild(h1Obj[0]);
	elements.append = function (childs) {
		for (let el of this) {
				for (let child of childs) {
				el.appendChild(child.cloneNode(true));
				//cloneNode(true) 이렇게 하면 객체 복사 (이동이 아닌)
				// 속성 ture는 어트리뷰트 및 텍스트 까지 복사
			}
		}
		return this;
	};

	elements.remove = function () {
		for (let el of this) {
			el.remove();
		}
	};

	elements.css = function (name, value) {
		// 객체인 경우
		if (typeof (name) == "object") {
			for (let el of this) {
				for (let key in name) {
					el.style[key] = name[key];
				}
			}
			return this;
		}
		// 객체가 아닌 경우
		else {
			if (value != undefined) {
				for (let el of this) {
					el.style[name] = value;
				}
				return this;
			}
			return this[0].style[name];
		}
	};

	elements.prop = function (name, value) {
		if (value != undefined) {
			for (let el of this) {
				el[name] = value;
			}
			return this;
		}
		return this[0][name];
	};

	elements.on = function (eventType, cb) {
		for (let el of this) {
			el.addEventListener(eventType, cb)
		}
		return this;
	};

	elements.click = function (cb) {
		return this.on("click", cb);
	};

	elements.focus = function () {
		this[0].focus();
		return this;
	};



	return elements;
}

var $ = jquery;
