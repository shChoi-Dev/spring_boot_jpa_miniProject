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
					<label>수량 : </label> <input type="number" id="cartQty" value="1"
						min="1" max="${product.prdStock}"
						style="width: 50px; text-align: center; padding: 5px;">
				</div>

				<button class="buy-button" id="addCartBtn"
					data-prdno="${product.prdNo}">장바구니 담기</button>
			</div>
		</div>

		<hr style="margin: 50px 0; border: 0; border-top: 1px solid #eee;">

		<div class="review-section"
			style="max-width: 1000px; margin: 0 auto 100px; padding: 0 20px; box-sizing: border-box;">
			<h3
				style="font-size: 1.5rem; font-weight: bold; margin-bottom: 20px; max-width: 1000px;">
				⭐ 구매 후기 (${reviewList.size()}개)</h3>

			<c:choose>
				<c:when test="${empty reviewList}">
					<div
						style="padding: 30px; text-align: center; color: #777; background: #f9f9f9; border-radius: 5px;">
						아직 등록된 리뷰가 없습니다. 첫 번째 리뷰를 남겨보세요!</div>
				</c:when>
				<c:otherwise>
					<table style="width: 100%; border-collapse: collapse;">
						<c:forEach var="review" items="${reviewList}">
							<div class="review-item"
								style="border-bottom: 1px solid #eee; padding: 15px 0;">
								<p>
									<strong>${review.member.memName}</strong> <span
										style="color: #f1c40f;">★ ${review.reviewScore}</span> <span
										style="color: #999; font-size: 0.9em; margin-left: 10px;">
										<fmt:formatDate value="${review.reviewDate}"
											pattern="yyyy-MM-dd" />
									</span>
								</p>

								<p>${review.reviewContent}</p>

								<c:if test="${not empty review.reviewImg}">
									<div style="margin-top: 10px;">
										<img src="<c:url value='/review_images/${review.reviewImg}'/>"
											width="100" height="100"
											style="object-fit: cover; border-radius: 5px;">
									</div>
								</c:if>

								<c:if test="${sessionScope.sid == review.member.memId}">
									<div style="text-align: right;">
										<button type="button"
											onclick="location.href='<c:url value='/review/updateForm?revNo=${review.reviewNo}'/>'"
											style="background: #3498db; color: white; border: none; padding: 5px 10px; border-radius: 3px; cursor: pointer; margin-right: 5px;">
											수정</button>
										<button type="button"
											onclick="if(confirm('정말 삭제하시겠습니까?')) location.href='<c:url value='/review/delete?revNo=${review.reviewNo}&prdNo=${product.prdNo}'/>'"
											style="background: #e74c3c; color: white; border: none; padding: 5px 10px; border-radius: 3px; cursor: pointer;">
											삭제</button>
									</div>
								</c:if>
							</div>
						</c:forEach>
					</table>
				</c:otherwise>
			</c:choose>

			<script src="<c:url value='/js/jquery-3.7.1.min.js'/>"></script>
			<script src="<c:url value='/js/cart.js'/>"></script>

		</div>
	</main>

	<%@ include file="/WEB-INF/views/include/bottom.jsp"%>
</body>
</html>