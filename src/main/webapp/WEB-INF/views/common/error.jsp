<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TRVR - 오류 발생</title>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<style>
.error-container {
	text-align: center;
	padding: 100px 20px;
	max-width: 600px;
	margin: 0 auto;
}

.error-code {
	font-size: 6rem;
	font-weight: bold;
	color: #e74c3c;
	margin: 0;
}

.error-message {
	font-size: 1.5rem;
	font-weight: bold;
	margin-top: 10px;
	color: #333;
}

.error-detail {
	color: #777;
	margin-top: 10px;
	margin-bottom: 40px;
	line-height: 1.6;
}

.btn-home {
	display: inline-block;
	padding: 12px 30px;
	background-color: #333;
	color: white;
	text-decoration: none;
	border-radius: 5px;
	font-weight: bold;
	transition: background 0.3s;
}

.btn-home:hover {
	background-color: #555;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/top.jsp"%>

	<main>
		<div class="error-container">
			<h1 class="error-code">${errorCode}</h1>

			<p class="error-message">${errorMessage}</p>

			<p class="error-detail">${errorDetail}</p>

			<a href="<c:url value='/'/>" class="btn-home">메인으로 가기</a>
		</div>
	</main>

	<%@ include file="/WEB-INF/views/include/bottom.jsp"%>
</body>
</html>