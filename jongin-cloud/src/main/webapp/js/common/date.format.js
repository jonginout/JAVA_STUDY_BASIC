//1511858834526
//같은 형식의 날짜 데이터 컨버터
//by 종인

function dateFormat (d) {
	 var dayWeek = ["일","월","화","수","목","금","토"];
	
	 var date = "\/Date("+d+")\/"
	 var dateString = date.substr(6);
	 var currentTime = new Date(parseInt(dateString ));
	 var year = currentTime.getFullYear();
	 var month = currentTime.getMonth() + 1;
	 var day = currentTime.getDate();
	 var dayW = dayWeek[currentTime.getDay()];
	 
	 var hour = ""+((currentTime.getHours() + 24) % 12 || 12);
 	 hour = hour.length < 2 ? "0"+hour : hour;
	 var min = ""+currentTime.getMinutes()
	 min = min.length < 2 ? "0"+min : min;
	 var sec = ""+currentTime.getSeconds()
	 sec = sec.length < 2 ? "0"+sec : sec;
	 var result = year +"-"+ month + "-" + day + " ("+ dayW + ") " + hour+":"+min+":"+sec
	 return result;
}