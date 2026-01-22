<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>TRVR - 당신의 모든 여행의 시작</title>

<%-- head.jsp --%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>

</head>
<body>

	<%-- top.jsp (헤더) --%>
	<%@ include file="/WEB-INF/views/include/top.jsp"%>

	<main>
		<section id="slideShowBox">
			<%-- 슬라이드 쇼 이미지 --%>
			<div id="slideShow">
				<a href="#"><img src="<c:url value='/image/slideImg1.png'/>"></a>
				<a href="#"><img src="<c:url value='/image/slideImg2.png'/>"></a>
				<a href="#"><img src="<c:url value='/image/slideImg3.png'/>"></a>
				<a href="#"><img src="<c:url value='/image/slideImg4.png'/>"></a>
				<a href="#"><img src="<c:url value='/image/slideImg5.png'/>"></a>
			</div>

			<%-- 좌우 화살표 --%>
			<a class="prev">&#10094;</a> <a class="next">&#10095;</a>

			<%-- 하단 인디케이터 --%>
			<div class="indicators"></div>
		</section>

		<section class="product-list">

			<section class="product-list" style="margin-top: 50px;">
				<h2 style="text-align: center; margin-bottom: 20px;">최신 상품</h2>
				<div class="items">
					<c:forEach var="product" items="${newProducts}">
						<a href="<c:url value='/product/detail/${product.prdNo}'/>"
							class="item-link">
							<div class="item">
								<img src="<c:url value='/prd_images/${product.prdImg}'/>"
									alt="${product.prdName}">
								<h3>${product.prdName}</h3>
								<p>
									<fmt:formatNumber value="${product.prdPrice}" pattern="#,###" />
									원
								</p>
							</div>
						</a>
					</c:forEach>
				</div>
			</section>

			<section class="product-list"
				style="margin-top: 50px; margin-bottom: 50px;">
				<h2 style="text-align: center; margin-bottom: 20px;">인기 상품</h2>
				<div class="items">
					<c:forEach var="product" items="${bestProducts}">
						<a href="<c:url value='/product/detail/${product.prdNo}'/>"
							class="item-link">
							<div class="item">
								<img src="<c:url value='/prd_images/${product.prdImg}'/>"
									alt="${product.prdName}">
								<h3>${product.prdName}</h3>
								<p>
									<fmt:formatNumber value="${product.prdPrice}" pattern="#,###" />
									원
								</p>
							</div>
						</a>
					</c:forEach>
				</div>
			</section>

			<section class="reviews">
				<h2>Best Reviews</h2>
				<div class="review-items">
					<c:forEach var="review" items="${reviewList}">
						<div class="review-item">
							<p>"${review.reviewContent}"</p>

							<span> <c:forEach begin="1" end="${review.reviewScore}">★</c:forEach>
								| ${review.member.memName} 고객님
							</span>
						</div>
					</c:forEach>
				</div>
			</section>
	</main>

	<%-- bottom.jsp (푸터) --%>
	<%@ include file="/WEB-INF/views/include/bottom.jsp"%>

</body>
</html>
