<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 대시보드</title>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<style>
.dashboard-container {
	width: 90%;
	margin: 50px auto;
}

.welcome-msg {
	font-size: 1.5rem;
	margin-bottom: 30px;
	color: #333;
}

/* 대시보드 카드 그리드 레이아웃 */
.dashboard-grid {
	display: grid;
	grid-template-columns: repeat(4, 1fr); /* 4열 배치 */
	gap: 20px;
	margin-bottom: 50px;
}

.card {
	background: white;
	padding: 30px 20px;
	border-radius: 10px;
	box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
	text-align: center;
	transition: transform 0.3s;
	border-top: 5px solid #333; /* 기본 포인트 컬러 */
}

.card:hover {
	transform: translateY(-5px);
}

.card-title {
	font-size: 1.1rem;
	color: #777;
	margin-bottom: 10px;
	font-weight: bold;
}

.card-number {
	font-size: 2.5rem;
	font-weight: bold;
	color: #333;
}

/* 카드별 포인트 컬러 */
.card.members {
	border-color: #3498db;
}

.card.members .card-number {
	color: #3498db;
}

.card.products {
	border-color: #2ecc71;
}

.card.products .card-number {
	color: #2ecc71;
}

.card.orders {
	border-color: #f1c40f;
}

.card.orders .card-number {
	color: #f1c40f;
}

.card.pending {
	border-color: #e74c3c;
	background-color: #fff5f5;
	cursor: pointer;
}

.card.pending .card-number {
	color: #e74c3c;
}

/* 바로가기 메뉴 섹션 */
.shortcut-section {
	display: flex;
	gap: 20px;
	justify-content: center;
}

.btn-shortcut {
	padding: 15px 40px;
	background: #555;
	color: white;
	text-decoration: none;
	border-radius: 5px;
	font-size: 1.1rem;
}

.btn-shortcut:hover {
	background: #333;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/top.jsp"%>

	<div class="dashboard-container">
		<div class="welcome-msg">
			👋 안녕하세요, <strong>관리자</strong>님! 오늘의 현황입니다.
		</div>

		<div class="dashboard-grid">
			<div class="card members">
				<div class="card-title">총 회원수</div>
				<div class="card-number">
					<fmt:formatNumber value="${memberCount}" />
				</div>
			</div>

			<div class="card products">
				<div class="card-title">등록 상품</div>
				<div class="card-number">
					<fmt:formatNumber value="${productCount}" />
				</div>
			</div>

			<div class="card orders">
				<div class="card-title">누적 주문</div>
				<div class="card-number">
					<fmt:formatNumber value="${orderCount}" />
				</div>
			</div>

			<div class="card pending"
				onclick="location.href='<c:url value='/admin/orders'/>'">
				<div class="card-title">🚚 배송 대기</div>
				<div class="card-number">
					<fmt:formatNumber value="${pendingCount}" />
				</div>
			</div>
		</div>

		<h3 style="text-align: center; margin-bottom: 20px;">바로가기 메뉴</h3>
		<div class="shortcut-section">
			<a href="<c:url value='/admin/products'/>" class="btn-shortcut">상품
				관리</a> <a href="<c:url value='/admin/orders'/>" class="btn-shortcut">주문
				관리</a>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/include/bottom.jsp"%>
</body>
</html>