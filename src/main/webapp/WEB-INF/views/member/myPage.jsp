<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TRVR - 마이페이지</title>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<style>
.mypage-container {
	width: 80%;
	margin: 50px auto;
	min-height: 600px;
}

/* 섹션 제목 스타일 */
.section-title {
	font-size: 1.5rem;
	font-weight: bold;
	margin: 40px 0 15px;
	padding-bottom: 10px;
	border-bottom: 2px solid #333;
	display: flex;
	justify-content: space-between;
	align-items: end;
}

/* 내 정보 테이블 */
.info-table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 30px;
}

.info-table th {
	width: 150px;
	background-color: #f8f9fa;
	padding: 15px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

.info-table td {
	padding: 15px;
	border-bottom: 1px solid #ddd;
}

/* 주문 내역 테이블 */
.order-table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 10px;
	table-layout: fixed;
}

.order-table th, .order-table td {
	padding: 12px;
	border-bottom: 1px solid #ddd;
	text-align: center;
}

.order-table th {
	background-color: #f1f1f1;
	border-top: 1px solid #333;
}

/* 주문 상태 뱃지 */
.status-badge {
	display: inline-block;
	padding: 5px 10px;
	border-radius: 15px;
	font-size: 0.85rem;
	font-weight: bold;
	color: white;
	background-color: #2ecc71; /* 기본(주문완료) : 녹색 */
}

/* 버튼 스타일 */
.btn-detail {
	padding: 5px 10px;
	border: 1px solid #ddd;
	background: white;
	cursor: pointer;
	border-radius: 3px;
	font-size: 0.9rem;
}

.btn-detail:hover {
	background: #f9f9f9;
	border-color: #bbb;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/top.jsp"%>

	<main>
		<div class="mypage-container">
			<h2>👤 마이페이지</h2>

			<div class="section-title">내 정보</div>
			<table class="info-table">
				<tr>
					<th>이름</th>
					<td>${member.memName}(${member.memRole})</td>
				</tr>
				<tr>
					<th>아이디</th>
					<td>${member.memId}</td>
				</tr>
				<tr>
					<th>연락처</th>
					<td>${member.memHp}</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>(${member.memZipcode}) ${member.memAddress1}
						${member.memAddress2}</td>
				</tr>
				<tr>
					<th>가입일</th>
					<td><fmt:formatDate value="${member.memJoindate}"
							pattern="yyyy-MM-dd" /></td>
				</tr>
			</table>

			<div class="section-title">
				<span>📦 최근 주문 내역</span> <span
					style="font-size: 0.9rem; font-weight: normal; color: #777;">최근
					주문순</span>
			</div>

			<table class="order-table">
				<thead>
					<tr>
						<th style="width: 20%;">주문일자</th>
						<th style="width: 15%;">주문번호</th>
						<th style="width: 25%;">받는 분</th>
						<th style="width: 15%;">결제금액</th>
						<th style="width: 15%;">상태</th>
						<th style="width: 10%;">관리</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${empty orderList}">
							<tr>
								<td colspan="6" style="padding: 50px 0; color: #777;">주문
									내역이 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="order" items="${orderList}">
								<tr>
									<td><fmt:formatDate value="${order.ordDate}"
											pattern="yyyy-MM-dd HH:mm" /></td>
									<td style="font-weight: bold; color: #333;">${order.ordNo}</td>
									<td>${order.ordName}</td>
									<td style="font-weight: bold;"><fmt:formatNumber
											value="${order.ordTotalPrice}" pattern="#,###" />원</td>
									<td><span class="status-badge">${order.ordStatus}</span></td>
									<td>
										<button class="btn-detail"
											onclick="location.href='<c:url value='/order/detail?ordNo=${order.ordNo}'/>'">상세보기</button>
									</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</main>

	<%@ include file="/WEB-INF/views/include/bottom.jsp"%>
</body>
</html>