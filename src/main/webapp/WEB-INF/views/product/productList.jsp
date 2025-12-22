<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TRVR - ${category}</title>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
</head>
<body>
    <%@ include file="/WEB-INF/views/include/top.jsp" %>
    <main>
        <section class="product-list">
            <h2>${category}</h2>
            <div class="items">
                <c:forEach var="product" items="${prdList}">
                    <a href="<c:url value='/product/detail/${product.prdNo}'/>" class="item-link">
                        <div class="item">
                            <img src="<c:url value='/prd_images/${product.prdImg}'/>" alt="${product.prdName}">
                            <h3>${product.prdName}</h3>
                            <p><fmt:formatNumber value="${product.prdPrice}" pattern="#,###" />원</p>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </section>
    </main>
    <%@ include file="/WEB-INF/views/include/bottom.jsp" %>
</body>
</html>