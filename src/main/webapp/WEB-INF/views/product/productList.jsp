<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>
    TRVR - 
    <c:choose>
        <%-- 검색어가 있으면: TRVR - "검색어" 검색 결과 --%>
        <c:when test="${not empty searchKeyword}">
            ${searchKeyword} 검색 결과
        </c:when>
        <%-- 검색어가 없으면: TRVR - 카테고리명 (예: 캐리어) --%>
        <c:otherwise>
            ${categoryName}
        </c:otherwise>
    </c:choose>
</title>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/top.jsp"%>
	<main>
		<section class="product-list">
			<h2>
				<c:choose>
					<%-- 검색어가 있을 경우 --%>
					<c:when test="${not empty searchKeyword}">
                        '${searchKeyword}' 검색 결과
                    </c:when>
					<%-- 검색어가 없을 경우 (기존 카테고리 표시) --%>
					<c:otherwise>
                        ${categoryName}
                    </c:otherwise>
				</c:choose>
			</h2>

			<c:if test="${empty prdList}">
				<div style="text-align: center; padding: 50px 0; color: #888;">
					<p style="font-size: 1.2rem;">검색된 상품이 없습니다.</p>
					<p style="font-size: 0.9rem; margin-top: 10px;">다른 검색어로 다시 시도해
						보세요.</p>
					<a href="<c:url value='/'/>"
						style="display: inline-block; margin-top: 20px; padding: 10px 20px; background: #333; color: white; text-decoration: none; border-radius: 5px;">메인으로
						돌아가기</a>
				</div>
			</c:if>
			
			<div class="items">
				<c:forEach var="product" items="${prdList}">
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
	</main>
	<%@ include file="/WEB-INF/views/include/bottom.jsp"%>
</body>
</html>