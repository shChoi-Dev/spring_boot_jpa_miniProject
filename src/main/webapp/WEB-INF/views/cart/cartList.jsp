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

<link rel="stylesheet" href="<c:url value='/css/cart.css'/>">

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
								<th style="width: 5%;"><input type="checkbox" id="checkAll"
									checked></th>
								<th style="width: 10%;">이미지</th>
								<th style="width: 40%;">상품명</th>
								<th style="width: 15%;">단가</th>
								<th style="width: 10%;">수량</th>
								<th style="width: 15%;">합계</th>
								<th style="width: 5%;">관리</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="cart" items="${cartList}">
								<tr>
									<td><input type="checkbox" name="cartCheck"
											   class="cart-check" value="${cart.cartNo}"
											   data-price="${cart.product.prdPrice}"
											   data-qty="${cart.cartQty}" checked></td>
									<td><img
										src="<c:url value='/prd_images/${cart.product.prdImg}'/>"
										class="cart-img"></td>
									<td style="text-align: left; font-weight: bold;">
										${cart.product.prdName}</td>
									<td><fmt:formatNumber value="${cart.product.prdPrice}"
											pattern="#,###" />원</td>
									<td>
										<div class="qty-box">
											<button type="button" class="qty-btn" onclick="changeQty(${cart.cartNo}, -1)">-</button>
        
        									<input type="number" id="qty_${cart.cartNo}" value="${cart.cartQty}" readonly class="qty-input">
        
        									<button type="button" class="qty-btn" onclick="changeQty(${cart.cartNo}, 1)">+</button>
										</div>
									</td>
									<td><span id="sum_${cart.cartNo}"> <fmt:formatNumber
												value="${cart.product.prdPrice * cart.cartQty}"
												pattern="#,###" />
									</span>원</td>
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
						총 결제 예정 금액: <span id="totalPrice" style="color: #e74c3c;">
							<fmt:formatNumber value="${totalPrice}" pattern="#,###" />
						</span>원
					</div>

					<div style="text-align: center;">
						<button class="btn-order" onclick="orderSelected()">선택 상품
							주문하기</button>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</main>

	<script src="<c:url value='/js/jquery-3.7.1.min.js'/>"></script>
	<script src="<c:url value='/js/cartList.js'/>"></script>

	<%@ include file="/WEB-INF/views/include/bottom.jsp"%>
</body>

</html>