<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TRVR - 당신의 모든 여행의 시작</title>

	<%-- head.jsp --%>
    <%@ include file="/WEB-INF/views/include/head.jsp" %>

    </head>
<body>

	<%-- top.jsp (헤더) --%>
    <%@ include file="/WEB-INF/views/include/top.jsp" %>
    
    <main>
    <section id="slideShowBox">
	    <div id="slideShow">
	        <a href="#"><img src="<c:url value='/image/slideImg1.png'/>"></a>
	        <a href="#"><img src="<c:url value='/image/slideImg2.png'/>"></a>
	        <a href="#"><img src="<c:url value='/image/slideImg3.png'/>"></a>
	        <a href="#"><img src="<c:url value='/image/slideImg4.png'/>"></a>
	        <a href="#"><img src="<c:url value='/image/slideImg5.png'/>"></a>
	    </div>
	</section>

        <section class="product-list">
            <h2>인기 상품</h2>
            <div class="items">
	           	<%-- 컨트롤러에서 전달받은 productList를 반복 처리 --%>
		        <c:forEach var="product" items="${prdList}">
		        	<a href="<c:url value='/product/detail/${product.prdNo}'/>" class="item-link">
			            <div class="item">
			                <%-- 상품 이미지 경로 설정 --%>
			                <img src="<c:url value='/prd_images/${product.prdImg}'/>" alt="${product.prdName}">
			                <%-- 상품 이름 출력 --%>
			                <h3>${product.prdName}</h3>
			                <%-- 상품 가격 출력 --%>
			                <p><fmt:formatNumber value="${product.prdPrice}" pattern="#,###" />원</p>
			            </div>
		            </a>
		        </c:forEach>
            </div>
        </section>

        <section class="reviews">
            <h2>Best Reviews</h2>
            <div class="review-items">
                <div class="review-item">
                    <p>"이 캐리어 정말 튼튼하고 좋아요! 추천합니다."</p>
                    <span>★★★★★ by user123</span>
                </div>
                <div class="review-item">
                    <p>"여행용품은 여기서만 사요. 배송도 빠르고 만족!"</p>
                    <span>★★★★★ by travel_lover</span>
                </div>
                </div>
        </section>

    </main>

	<%-- bottom.jsp (푸터) --%>
    <%@ include file="/WEB-INF/views/include/bottom.jsp" %>

</body>
</html>
