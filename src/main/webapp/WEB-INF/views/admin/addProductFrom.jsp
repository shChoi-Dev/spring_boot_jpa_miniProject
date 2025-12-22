<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TRVR - 새 상품 등록</title>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/top.jsp" %>
	<main>
		<div class="form-container">
            <h2 class="form-title">새 상품 등록</h2>
            <%-- 이미지를 포함한 폼 전송을 위해 enctype="multipart/form-data" 추가 --%>
            <form action="<c:url value='/admin/products/new'/>" method="post" enctype="multipart/form-data">
	            <div class="form-group">
			        <label class="form-label">상품 번호</label>
			        <input type="number" name="prdNo" class="form-input" required>
			    </div>
                <div class="form-group">
                    <label class="form-label">상품명</label>
                    <input type="text" name="prdName" class="form-input" required>
                </div>
                <div class="form-group">
                    <label class="form-label">가격</label>
                    <input type="number" name="prdPrice" class="form-input" required>
                </div>
                <div class="form-group">
                    <label class="form-label">재고</label>
                    <input type="number" name="prdStock" class="form-input" required>
                </div>
                <div class="form-group">
                    <label class="form-label">상품 설명</label>
                    <textarea name="prdDesc" class="form-input" rows="5"></textarea>
                </div>
                <div class="form-group">
                    <label class="form-label">상품 이미지</label>
                    <input type="file" name="file" class="form-input">
                </div>
                <input type="submit" value="등록하기" class="form-button">
            </form>
        </div>
	</main>
	<%@ include file="/WEB-INF/views/include/bottom.jsp" %>
</body>
</html>