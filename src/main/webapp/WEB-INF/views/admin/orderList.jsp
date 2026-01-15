<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 - 주문 관리</title>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<style>
.admin-container {
	width: 90%;
	margin: 50px auto;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

th, td {
	padding: 12px;
	border-bottom: 1px solid #ddd;
	text-align: center;
}

th {
	background: #f8f9fa;
	border-top: 2px solid #333;
}

.status-form {
	display: flex;
	gap: 5px;
	justify-content: center;
}

.btn-update {
	padding: 5px 10px;
	background: #333;
	color: white;
	border: none;
	cursor: pointer;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/top.jsp"%>

	<div class="admin-container">
		<h2>📦 전체 주문 관리</h2>

		<div style="margin: 20px 0;">
			<a href="<c:url value='/admin/products'/>"
				style="margin-right: 15px; color: #888;">상품 관리</a> <a
				href="<c:url value='/admin/orders'/>"
				style="font-weight: bold; color: #333; text-decoration: underline;">주문
				관리</a>
		</div>

		<table>
			<thead>
				<tr>
					<th>주문번호</th>
					<th>주문일시</th>
					<th>주문자(ID)</th>
					<th>주문금액</th>
					<th>받는분</th>
					<th>현재상태</th>
					<th>상태변경</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="order" items="${orderList}">
					<tr>
						<td>${order.ordNo}</td>
						<td><fmt:formatDate value="${order.ordDate}"
								pattern="yyyy-MM-dd HH:mm" /></td>
						<td>${order.member.memName}(${order.member.memId})</td>
						<td><fmt:formatNumber value="${order.ordTotalPrice}"
								pattern="#,###" />원</td>
						<td>${order.ordName}</td>
						<td style="font-weight: bold; color: #007bff;">${order.ordStatus}</td>
						<td>
							<form action="<c:url value='/admin/orders/update'/>"
								method="post" class="status-form">
								<input type="hidden" name="ordNo" value="${order.ordNo}">
								<select name="status" style="padding: 5px;">
									<option value="ORDERED"
										${order.ordStatus == 'ORDERED' ? 'selected' : ''}>주문완료</option>
									<option value="SHIPPING"
										${order.ordStatus == 'SHIPPING' ? 'selected' : ''}>배송중</option>
									<option value="DELIVERED"
										${order.ordStatus == 'DELIVERED' ? 'selected' : ''}>배송완료</option>
									<option value="CANCELED"
										${order.ordStatus == 'CANCELED' ? 'selected' : ''}>주문취소</option>
								</select>
								<button type="submit" class="btn-update">변경</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<%@ include file="/WEB-INF/views/include/bottom.jsp"%>
</body>
</html>