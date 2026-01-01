<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TRVR - 주문서 작성</title>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<style>
.order-container {
	width: 80%;
	margin: 50px auto;
}

.section-title {
	font-size: 1.5rem;
	font-weight: bold;
	margin: 30px 0 15px;
	border-bottom: 2px solid #333;
	padding-bottom: 10px;
}

/* 주문 상품 테이블 스타일 */
.order-table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 30px;
}

.order-table th, .order-table td {
	border-bottom: 1px solid #ddd;
	padding: 12px;
	text-align: center;
}

.order-table th {
	background-color: #f8f9fa;
}

.prd-img {
	width: 60px;
	height: 60px;
	object-fit: cover;
	border-radius: 4px;
}

/* 배송지 입력 폼 스타일 */
.delivery-table {
	width: 100%;
	border-collapse: collapse;
}

.delivery-table th {
	width: 150px;
	text-align: left;
	padding: 15px;
	background-color: #f9f9f9;
	border-bottom: 1px solid #ddd;
}

.delivery-table td {
	padding: 15px;
	border-bottom: 1px solid #ddd;
}

.input-text {
	width: 300px;
	padding: 8px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

.input-addr {
	width: 500px;
	margin-top: 5px;
}

/* 결제 박스 스타일 */
.pay-box {
	text-align: center;
	margin-top: 50px;
	padding: 30px;
	background-color: #f1f1f1;
	border-radius: 10px;
}

.total-price {
	font-size: 2rem;
	color: #e74c3c;
	font-weight: bold;
	margin-left: 10px;
}

.btn-pay {
	background-color: #3498db;
	color: white;
	border: none;
	padding: 15px 50px;
	font-size: 1.3rem;
	cursor: pointer;
	border-radius: 5px;
	margin-top: 20px;
	transition: 0.3s;
}

.btn-pay:hover {
	background-color: #2980b9;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/top.jsp"%>

	<main>
		<div class="order-container">
			<h2>📑 주문서 작성</h2>

			<div class="section-title">주문 상품 정보</div>
			<table class="order-table">
				<thead>
					<tr>
						<th>이미지</th>
						<th>상품명</th>
						<th>단가</th>
						<th>수량</th>
						<th>합계</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="cart" items="${cartList}">
						<tr>
							<td><img
								src="<c:url value='/prd_images/${cart.product.prdImg}'/>"
								class="prd-img"></td>
							<td style="text-align: left; font-weight: bold;">${cart.product.prdName}</td>
							<td><fmt:formatNumber value="${cart.product.prdPrice}"
									pattern="#,###" />원</td>
							<td>${cart.cartQty}개</td>
							<td><fmt:formatNumber
									value="${cart.product.prdPrice * cart.cartQty}" pattern="#,###" />원</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<div class="section-title">배송지 정보</div>
			<form action="/order/insert" method="post" id="orderForm">

				<c:forEach var="cart" items="${cartList}">
					<input type="hidden" name="cartNos" value="${cart.cartNo}">
				</c:forEach>

				<table class="delivery-table">
					<tr>
						<th>받는 사람</th>
						<td><input type="text" name="ordName" class="input-text"
							value="${member.memName}" required></td>
					</tr>
					<tr>
						<th>연락처</th>
						<td><input type="text" name="ordPhone" class="input-text"
							value="${member.memHp}" required></td>
					</tr>
					<tr>
						<th>주소</th>
						<td><input type="text" name="ordZipcode" class="input-text"
							value="${member.memZipcode}" placeholder="우편번호"
							style="width: 100px;" readonly> <input type="button"
							value="주소 찾기" onclick="findAddr()" style="padding: 8px;"><br>
							<input type="text" name="ordAddr1" class="input-text input-addr"
							value="${member.memAddress1}" placeholder="기본 주소" readonly><br>
							<input type="text" name="ordAddr2" class="input-text input-addr"
							value="${member.memAddress2}" placeholder="상세 주소">
					</tr>
				</table>

				<div class="pay-box">
					<div>
						최종 결제 금액 <span class="total-price"><fmt:formatNumber
								value="${totalPrice}" pattern="#,###" /></span>원
					</div>
					<button type="submit" class="btn-pay">결제하기</button>
				</div>
			</form>
		</div>
	</main>

	<script
		src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		function findAddr() {
			new daum.Postcode(
					{
						oncomplete : function(data) {
							// 도로명 주소와 지번 주소 구분
							var addr = '';
							if (data.userSelectedType === 'R') { // 도로명 주소 선택
								addr = data.roadAddress;
							} else { // 지번 주소 선택
								addr = data.jibunAddress;
							}

							// 입력칸에 값 넣기 (name 속성으로 찾아서 넣음)
							document.querySelector("input[name=ordZipcode]").value = data.zonecode; // 우편번호
							document.querySelector("input[name=ordAddr1]").value = addr; // 기본주소

							// 상세주소 입력칸으로 포커스 이동
							document.querySelector("input[name=ordAddr2]")
									.focus();
						}
					}).open();
		}
	</script>

	<%@ include file="/WEB-INF/views/include/bottom.jsp"%>
</body>
</html>