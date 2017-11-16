<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="navi" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.btn:hover{
		cursor : pointer;
		background: skyblue;
	}
	.btn{
		padding: 2px !important;
    	border-radius: 25px;
	} 
</style>
</head>
<body>
	<c:import url="/include/basicInclude.jsp" />
	
	<form name="searchFrom" action="" onsubmit="return false;">
		<table border="1">
			<tr>
				<td colspan="6">사원 정보 검색</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>
					<input type="text" name="staffName" id="staffName" value=""/>
				</td>
				<td>성별</td>
				<td>
					<label for="m">남자</label>
					<input type="radio" name="juminNoSt" id="m" value="1"/>
					<label for="f">여자</label>
					<input type="radio" name="juminNoSt" id="f" value="2"/>
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
						<input type="radio" name="schoolCode" value="${s.schoolCode}" id="${s.schoolName}" />
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
					일~
					
					<select name="year2">
						<c:forEach var="i" begin="0" end="${2018-1900}">
						<c:set var="yearOption" value="${2018-i}" />
						<option value="${yearOption}">${yearOption}</option>
					</c:forEach>
					</select>
					 년 
					<select name="month2">
						<c:forEach var="i" begin="1" end="12">
							<c:set var="month" value="0${i}"/>
							<c:set var="month" value="${fn:substring(month, fn:length(month)-2, fn:length(month) )}"/>
							<option value="${month}">${month}</option>
						</c:forEach>
					</select>
					월
					<select name="day2">
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
		<button type="button" onclick="search()">검색</button>
		<button type="button">전부검색</button>
		<button type="button" onclick="clean()">초기화</button>
		<button type="button" onclick="insert()">등록</button>
		
		
		<input type="hidden" name="graduateDay1" value=""/>
		<input type="hidden" name="graduateDay2" value=""/>
	</form>
	
	<table border="1" style="margin-top: 50px">
	검색건수 ---> ${staffListCount} 건
		<tr>
			<th>번호</td>
			<th>이름</td>
			<th>성별</td>
			<th>종교</td>
			<th>졸업일</td>
			<th>수정/삭제</td>
		</tr>
		<c:forEach var="s" items="${staffList}">
			<tr>
				<th>${s.staffNo}</td>
				<th>${s.staffName}</td>
				<th>${s.juminNo}</td>
				<th>${s.religionCode}</td>
				<th>${s.graduateDay}</td>
				<th>
					<a class="btn" onclick="modifyStaff(${s.staffNo})">수정</a>
					/
					<a class="btn" onclick="deleteStaff('${s.staffName}',${s.staffNo})">삭제</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<navi:page data="${pageResult}" url="main.do"/>
	
</body>
</html>

<script type="text/javascript">
	
	function modifyStaff(staffNo) {
		location.href = 
			"${pageContext.request.contextPath}/search/modifyform.do?staffNo="+staffNo;
	}
	
	function deleteStaff(staffName,staffNo) {
		if(confirm('정말로'+staffName+'님을 삭제하시겠습니까?')){			
			location.href = 
				"${pageContext.request.contextPath}/search/delete.do?staffNo="+staffNo;
		}
	}
	
	function search() {
		
		var year = $("select[name=year]").val();
		var month = $("select[name=month]").val();
		var day = $("select[name=day]").val();
		
		var year2 = $("select[name=year2]").val();
		var month2 = $("select[name=month2]").val();
		var day2 = $("select[name=day2]").val();
		
		var graduateDay1 = year+"-"+month+"-"+day;
		var graduateDay2 = year2+"-"+month2+"-"+day2;
		
		if(graduateDay1 ==null || graduateDay2 == null){
			graduateDay1 = "1920-01-01";
			graduateDay2 = "2018-01-01";
		}
		
		$("input:hidden[name=graduateDay1]").val(graduateDay1);	
		$("input:hidden[name=graduateDay2]").val(graduateDay2);	
		
		searchFrom.submit();
	}

	
	var staffParam = '${param}';
	if(staffParam){
		
		//제이슨
		var skillCode = "${param.skillCode}";
		if (!skillCode.length<1) {			
		var skillCodes = ${skillCodes};
			for (var i = 0; i < skillCodes.length; i++) {
				$("input:checkbox[name=skillCode][value="+skillCodes[i]+"]").prop("checked", true);			
			}
		}
		
		var staffName = "${param.staffName}";
		if (!staffName.length<1) {			
			$("#staffName").val('${param.staffName}');
		}
		
		var juminNoSt = "${param.juminNoSt}";
		if (!juminNoSt.length<1) {			
			$("input:radio[name=juminNoSt][value="+'${param.juminNoSt}'+"]").prop("checked", true)
		}

		var religionCode = "${param.religionCode}";
		if (!religionCode.length<1) {			
			$("select[name=religionCode]").val('${param.religionCode}');
		}
		
		
		var schoolCode = "${param.schoolCode}";
		if (!schoolCode.length<1) {			
			$("input:radio[name=schoolCode][value="+'${param.schoolCode}'+"]").prop("checked", true)
		}
		
		var year = "${param.year}";
		if (!year.length<1) {			
			$("select[name=year]").val('${param.year}');
			$("select[name=month]").val('${param.month}');
			$("select[name=day]").val('${param.day}');
			$("select[name=year2]").val('${param.year2}');
			$("select[name=month2]").val('${param.month2}');
			$("select[name=day2]").val('${param.day2}');			
		}
	}
	function clean() {
		location.href = "${pageContext.request.contextPath}/search/main.do";
	}
	function insert() {
		location.href = "${pageContext.request.contextPath}/search/insertfrom.do";
	}
</script>