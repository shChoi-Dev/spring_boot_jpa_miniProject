<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TRVR - ${product.prdName}</title>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/top.jsp"%>

	<main>
		<div class="detail-container">
			<div class="detail-img">
				<img src="<c:url value='/prd_images/${product.prdImg}'/>"
					alt="${product.prdName}" style="width: 100%;">
			</div>
			<div class="detail-info">
				<h2>${product.prdName}</h2>
				<%-- 가격 출력 부분을 JSTL 포맷팅(fmt:formatNumber)으로 설정 --%>
				<p class="price">
					<fmt:formatNumber value="${product.prdPrice}" pattern="#,###" />
					원
				</p>
				<p>재고: ${product.prdStock}개</p>
				<hr>
				<p>${product.prdDesc}</p>

				<div style="margin: 20px 0;">
					<label>수량 : </label> 
					<input type="number" id="cartQty" value="1" min="1" max="${product.prdStock}" 
               			   style="width: 50px; text-align: center; padding: 5px;">
				</div>

				<button class="buy-button" id="addCartBtn" data-prdno="${product.prdNo}">장바구니 담기</button>
			</div>

			<script src="<c:url value='/js/jquery-3.7.1.min.js'/>"></script>
			<script src="<c:url value='/js/cart.js'/>"></script>

		</div>
	</main>

	<%@ include file="/WEB-INF/views/include/bottom.jsp"%>
</body>
</html>