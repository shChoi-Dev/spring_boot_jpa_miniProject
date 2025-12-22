<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TRVR - 상품 수정</title>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
</head>
<body>
    <%@ include file="/WEB-INF/views/include/top.jsp" %>
    <main>
        <div class="form-container">
            <h2 class="form-title">상품 수정</h2>
            <form action="<c:url value='/admin/products/edit'/>" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label class="form-label">상품 번호</label>
                    <%-- 상품 번호는 수정할 수 없도록 readonly 속성 추가 --%>
                    <input type="number" name="prdNo" class="form-input" value="${product.prdNo}" readonly>
                </div>
                <div class="form-group">
                    <label class="form-label">상품명</label>
                    <input type="text" name="prdName" class="form-input" value="${product.prdName}" required>
                </div>
                <div class="form-group">
                    <label class="form-label">가격</label>
                    <input type="number" name="prdPrice" class="form-input" value="${product.prdPrice}" required>
                </div>
                <div class="form-group">
                    <label class="form-label">재고</label>
                    <input type="number" name="prdStock" class="form-input" value="${product.prdStock}" required>
                </div>
                <div class="form-group">
                    <label class="form-label">상품 설명</label>
                    <textarea name="prdDesc" class="form-input" rows="5">${product.prdDesc}</textarea>
                </div>
                <div class="form-group">
                    <label class="form-label">상품 이미지 (변경 시에만 선택)</label>
                    <input type="file" name="file" class="form-input">
                    <p>현재 이미지: ${product.prdImg}</p>
                </div>
                <input type="submit" value="수정하기" class="form-button">
            </form>
        </div>
    </main>
    <%@ include file="/WEB-INF/views/include/bottom.jsp" %>
</body>
</html>