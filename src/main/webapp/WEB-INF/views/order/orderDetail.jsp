<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TRVR - 주문 상세</title>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<style>
.order-detail-container {
	width: 80%;
	margin: 50px auto;
}

.section-title {
	font-size: 1.4rem;
	font-weight: bold;
	margin: 40px 0 15px;
	border-bottom: 2px solid #333;
	padding-bottom: 10px;
}

/* 테이블 공통 스타일 */
.common-table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 20px;
}

.common-table th, .common-table td {
	border-bottom: 1px solid #ddd;
	padding: 12px;
}

.common-table th {
	background-color: #f8f9fa;
	text-align: left;
	width: 150px;
}

/* 상품 목록 테이블 스타일 */
.prd-table {
	width: 100%;
	border-collapse: collapse;
	table-layout: fixed;
}

.prd-table th, .prd-table td {
	border-bottom: 1px solid #ddd;
	padding: 12px;
	text-align: center;
}

.prd-table th {
	background-color: #f1f1f1;
	border-top: 1px solid #333;
}

.prd-img {
	width: 60px;
	height: 60px;
	object-fit: cover;
	border-radius: 4px;
}

/* 버튼 스타일 */
.btn-review {
	background-color: #3498db;
	color: white;
	border: none;
	padding: 5px 12px;
	border-radius: 3px;
	cursor: pointer;
	font-size: 0.9rem;
}

.btn-review:hover {
	background-color: #2980b9;
}

.btn-list {
	display: inline-block;
	padding: 10px 30px;
	background: #555;
	color: white;
	text-decoration: none;
	border-radius: 5px;
	margin-top: 30px;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/top.jsp"%>

	<main>
		<div class="order-detail-container">
			<h2>📄 주문 상세 내역</h2>

			<div class="section-title">주문 정보</div>
			<table class="common-table">
				<tr>
					<th>주문번호</th>
					<td>${order.ordNo}</td>
					<th>주문일자</th>
					<td><fmt:formatDate value="${order.ordDate}"
							pattern="yyyy-MM-dd HH:mm" /></td>
				</tr>
				<tr>
					<th>받는 사람</th>
					<td>${order.ordName}</td>
					<th>연락처</th>
					<td>${order.ordPhone}</td>
				</tr>
				<tr>
					<th>배송지</th>
					<td colspan="3">(${order.ordZipcode}) ${order.ordAddr1}
						${order.ordAddr2}</td>
				</tr>
				<tr>
					<th>총 결제금액</th>
					<td colspan="3" style="font-weight: bold; color: #e74c3c;"><fmt:formatNumber
							value="${order.ordTotalPrice}" pattern="#,###" />원</td>
				</tr>
			</table>

			<div class="section-title">주문 상품 목록</div>
			<table class="prd-table">
				<thead>
					<tr>
						<th style="width: 10%;">이미지</th>
						<th style="width: 40%;">상품명</th>
						<th style="width: 15%;">구매가</th>
						<th style="width: 10%;">수량</th>
						<th style="width: 15%;">합계</th>
						<th style="width: 10%;">리뷰</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="detail" items="${orderDetailList}">
						<tr>
							<td><img
								src="<c:url value='/prd_images/${detail.product.prdImg}'/>"
								class="prd-img"></td>
							<td style="text-align: left; font-weight: bold;"><a
								href="<c:url value='/product/detail/${detail.product.prdNo}'/>"
								style="color: #333; text-decoration: none;">
									${detail.product.prdName} </a></td>
							<td><fmt:formatNumber value="${detail.ordPrice}"
									pattern="#,###" />원</td>
							<td>${detail.ordQty}</td>
							<td><fmt:formatNumber
									value="${detail.ordPrice * detail.ordQty}" pattern="#,###" />원</td>
							<td>
								<button type="button" class="btn-review"
									onclick="location.href='<c:url value='/review/writeForm?prdNo=${detail.product.prdNo}&ordNo=${order.ordNo}'/>'">
									리뷰쓰기</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<div style="text-align: center;">
				<a href="<c:url value='/member/myPage'/>" class="btn-list">목록으로</a>
			</div>
		</div>
	</main>

	<%@ include file="/WEB-INF/views/include/bottom.jsp"%>
</body>
</html>