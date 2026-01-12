<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<style>
.join-container {
	width: 500px;
	margin: 50px auto;
}

.form-group {
	margin-bottom: 15px;
}

.form-group label {
	display: block;
	margin-bottom: 5px;
	font-weight: bold;
}

.form-group input {
	width: 100%;
	padding: 10px;
	border: 1px solid #ddd;
	border-radius: 4px;
	box-sizing: border-box;
}

.btn-update {
	width: 100%;
	padding: 15px;
	background: #333;
	color: white;
	border: none;
	font-size: 16px;
	cursor: pointer;
	margin-top: 10px;
}

/* 탈퇴 버튼 구역 */
.withdraw-section {
	margin-top: 50px;
	padding-top: 20px;
	border-top: 1px solid #eee;
	text-align: right;
}

.btn-withdraw {
	background: transparent;
	color: #dc3545;
	border: 1px solid #dc3545;
	padding: 5px 10px;
	cursor: pointer;
	font-size: 0.8rem;
}

.btn-withdraw:hover {
	background: #dc3545;
	color: white;
}
</style>
<script>
	function withdraw() {
		if (confirm("정말로 탈퇴하시겠습니까?\n탈퇴 시 복구할 수 없습니다.")) {
			// 탈퇴 폼 제출
			document.getElementById('withdrawForm').submit();
		}
	}
</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/top.jsp"%>

	<div class="join-container">
		<h2 style="text-align: center; margin-bottom: 30px;">회원 정보 수정</h2>

		<form action="<c:url value='/member/update'/>" method="post">
			<div class="form-group">
				<label>아이디</label> <input type="text" value="${member.memId}"
					readonly style="background: #f1f1f1;">
			</div>

			<div class="form-group">
				<label>비밀번호 (변경 시에만 입력)</label> <input type="password" name="memPwd"
					placeholder="변경하지 않으려면 비워두세요">
			</div>

			<div class="form-group">
				<label>이름</label> <input type="text" value="${member.memName}"
					readonly style="background: #f1f1f1;">
			</div>

			<div class="form-group">
				<label>이메일</label> <input type="email" name="memEmail"
					value="${member.memEmail}">
			</div>

			<div class="form-group">
				<label>연락처</label> <input type="text" name="memHp"
					value="${member.memHp}">
			</div>

			<div class="form-group">
				<label>주소</label> <input type="text" name="memZipcode"
					id="memZipcode" value="${member.memZipcode}" placeholder="우편번호"
					style="width: 30%; margin-bottom: 5px;">
				<button type="button" onclick="daumPostcode()" style="padding: 8px;">우편번호
					찾기</button>
				<br> <input type="text" name="memAddress1" id="memAddress1"
					value="${member.memAddress1}" placeholder="주소"
					style="margin-bottom: 5px;"><br> <input type="text"
					name="memAddress2" id="memAddress2" value="${member.memAddress2}"
					placeholder="상세주소">
			</div>

			<button type="submit" class="btn-update">정보 수정완료</button>
		</form>

		<div class="withdraw-section">
			<span style="font-size: 0.8rem; color: #777; margin-right: 10px;">더
				이상 이용하지 않으시나요?</span>
			<button type="button" class="btn-withdraw" onclick="withdraw()">회원
				탈퇴</button>
		</div>

		<form id="withdrawForm" action="<c:url value='/member/withdraw'/>"
			method="post"></form>
	</div>

	<%@ include file="/WEB-INF/views/include/bottom.jsp"%>

	<script
		src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		// 주소 찾기 함수 (기존 joinForm과 동일)
		function daumPostcode() {
			new daum.Postcode(
					{
						oncomplete : function(data) {
							var addr = '';
							if (data.userSelectedType === 'R') {
								addr = data.roadAddress;
							} else {
								addr = data.jibunAddress;
							}
							document.getElementById('memZipcode').value = data.zonecode;
							document.getElementById("memAddress1").value = addr;
							document.getElementById("memAddress2").focus();
						}
					}).open();
		}
	</script>
</body>
</html>