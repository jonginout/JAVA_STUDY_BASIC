<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:import url="/include/basicInclude.jsp" />
	
	<form action="${pageContext.request.contextPath}/search/insert.do"
	 method="post" name="insertForm">
		<table border="1">
			<tr>
				<td colspan="6">사원 정보 등록</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>
					<input type="text" name="staffName" id="staffName"/>
				</td>
				<td>주민번호</td>
				<td>
					<input type="text" name="juminNo1" id="juminNo1" size="6" maxlength="6"/>
					-
					<input type="password" name="juminNo2" id="juminNo2" size="7" maxlength="7"/>
				</td>
				<td>종교</td>
				<td>
					<select name="religionCode">
							<option value="0">종교선택</option>
						<c:forEach var="r" items="${religionList}">
							<option value="${r.religionCode}">${r.religionName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>학력</td>
				<td>
					<c:forEach var="s" items="${schoolList}">					
						<label for="${s.schoolName}">${s.schoolName}</label>
						<input type="radio" name="schoolCode" value="${s.schoolCode}" id="${s.schoolName}"/>
						|
					</c:forEach>
				</td>
				<td>기술</td>
				<td colspan="3">
					<c:forEach var="s" items="${skillList}">					
						<label for="${s.skillName}">${s.skillName}</label>
						<input type="checkbox" name="skillCode" value="${s.skillCode}" id="${s.skillName}"/>
						|
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td>졸업일</td>
				<td colspan="5">
				
					<select name="year">
						<c:forEach var="i" begin="1920" end="2018">
							<option value="${i}">${i}</option>
						</c:forEach>
					</select>
					년
					<select name="month">
						<c:forEach var="i" begin="1" end="12">						
							<c:set var="month" value="0${i}"/>
							<c:set var="month" value="${fn:substring(month, fn:length(month)-2, fn:length(month) )}"/>
							<option value="${month}">${month}</option>
						</c:forEach>
					</select>
					월
					<select name="day">
						<c:forEach var="i" begin="1" end="31">
							<c:set var="day" value="0${i}"/>
							<c:set var="day" value="${fn:substring(day, fn:length(day)-2, fn:length(day) )}"/>
							<option value="${day}">${day}</option>
						</c:forEach>
					</select>
					일
				</td>
			</tr>
		</table>
		<button type="button" onclick="insert()">등록</button>
		<button type="button" onclick="clean()">초기화</button>
		<button type="button" onclick="search()">뒤로</button>
		
		
		<input type="hidden" name="juminNo" value=""/>
		<input type="hidden" name="graduateDay" value=""/>
	</form>

	
<script type="text/javascript">

	function insert() {
		
		var staffNamePattern = /^([가-힣a-zA-Z]){2,10}$/;
		var juminNo1Pattern = /^([0-9]){6}$/;
		var juminNo2Pattern = /^([0-9]){7}$/;
		
		var staffName = $("#staffName").val();
		var juminNo1 = $("#juminNo1").val();
		var juminNo2 = $("#juminNo2").val();
		var religionCode = $("select[name=religionCode]").val();
		var schoolChk = false;
		var skillChk = false;
		
		if(!staffNamePattern.test(staffName)){
			alert("이름은 한글,영대소문자로 2~10자 입니다.");
			return;
		}
		if(!juminNo1Pattern.test(juminNo1)){
			alert("주민번호 앞자리를 올바르게 입력하세요.");
			return;
		}
		if(!juminNo2Pattern.test(juminNo2)){
			alert("주민번호 뒷자리를 올바르게 입력하세요");
			return;
		}
		if(religionCode==0){
			alert("종교를 선택하세요.");
			return;
		}
		$("input:radio[name=schoolCode]").each(function() {
			if (this.checked == true) { 
				schoolChk = true;
				return;
			}
		});
		if(!schoolChk){
			alert("학력을 선택하세요.");
			return;
		}
		$("input:checkbox[name=skillCode]").each(function() {
			if (this.checked == true) { 
				skillChk = true;
				return;
			}
		});
		if(!skillChk){
			alert("기술을 선택하세요.");
			return;
		}
			
		if(!confirm('정말로 스태프 정보를 등록하시겠습니까?')){
			return;
		}
			
		var jumin = juminNo1+"-"+juminNo2;
		
		var year = $("select[name=year]").val();
		var month = $("select[name=month]").val();
		var day = $("select[name=day]").val();
		var graduateDay = year+"-"+month+"-"+day;
		$("input:hidden[name=juminNo]").val(jumin);		
		$("input:hidden[name=graduateDay]").val(graduateDay);		
	
		insertForm.submit();			
	}

	function clean() {
		location.href = "${pageContext.request.contextPath}/search/insertfrom.do";
	}
	function search() {
		location.href = "${pageContext.request.contextPath}/search/main.do";
	}
</script>
</body>
</html>
