<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TRVR - 장바구니</title>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<style>
.cart-container {
	width: 80%;
	margin: 50px auto;
	min-height: 500px;
}

.cart-table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 20px;
}

.cart-table th, .cart-table td {
	border-bottom: 1px solid #ddd;
	padding: 15px;
	text-align: center;
}

.cart-table th {
	background-color: #f8f9fa;
	border-top: 2px solid #333;
}

.cart-img {
	width: 80px;
	height: 80px;
	object-fit: cover;
	border-radius: 5px;
}

.total-area {
	text-align: right;
	font-size: 1.5em;
	font-weight: bold;
	margin: 30px 0;
	padding: 20px;
	background: #f9f9f9;
	border-radius: 10px;
}

.btn-delete {
	background-color: #e74c3c;
	color: white;
	border: none;
	padding: 5px 10px;
	cursor: pointer;
	border-radius: 4px;
}

.btn-order {
	background-color: #3498db;
	color: white;
	border: none;
	padding: 15px 40px;
	font-size: 1.2em;
	cursor: pointer;
	border-radius: 5px;
	transition: 0.3s;
}

.btn-order:hover {
	background-color: #2980b9;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/top.jsp"%>

	<main>
		<div class="cart-container">
			<h2 style="margin-bottom: 30px;">🛒 나의 장바구니</h2>

			<c:choose>
				<%-- 장바구니가 비었을 때 --%>
				<c:when test="${empty cartList}">
					<div style="text-align: center; margin: 100px 0;">
						<p style="font-size: 1.2em; color: #777;">장바구니에 담긴 상품이 없습니다.</p>
						<a href="<c:url value='/'/>" class="btn-order"
							style="text-decoration: none; font-size: 1rem; padding: 10px 20px; background-color: #555;">쇼핑
							계속하기</a>
					</div>
				</c:when>

				<%-- 장바구니에 상품이 있을 때 --%>
				<c:otherwise>
					<table class="cart-table">
						<thead>
							<tr>
								<th>이미지</th>
								<th>상품명</th>
								<th>단가</th>
								<th>수량</th>
								<th>합계</th>
								<th>관리</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="cart" items="${cartList}">
								<tr>
									<td><img
										src="<c:url value='/prd_images/${cart.product.prdImg}'/>"
										class="cart-img"></td>
									<td style="text-align: left; font-weight: bold;">${cart.product.prdName}</td>
									<td><fmt:formatNumber value="${cart.product.prdPrice}"
											pattern="#,###" />원</td>
									<td>${cart.cartQty}</td>
									<td><fmt:formatNumber
											value="${cart.product.prdPrice * cart.cartQty}"
											pattern="#,###" />원</td>
									<td>
										<button type="button" class="btn-delete"
											onclick="if(confirm('정말 삭제하시겠습니까?')) location.href='/cart/delete?cartNo=${cart.cartNo}'">
											삭제</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<div class="total-area">
						총 결제 금액: <span style="color: #e74c3c;"><fmt:formatNumber
								value="${totalPrice}" pattern="#,###" /></span>원
					</div>

					<div style="text-align: center;">
						<button class="btn-order" onclick="alert('주문 기능은 다음 단계에서 구현됩니다!')">주문하기</button>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</main>

	<%@ include file="/WEB-INF/views/include/bottom.jsp"%>
</body>
</html>