<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TRVR - 관리자 페이지</title>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<style>
    .admin-container { max-width: 1200px; margin: 30px auto; padding: 20px; }
    .admin-title { font-size: 28px; margin-bottom: 20px; }
    .admin-table { width: 100%; border-collapse: collapse; }
    .admin-table th, .admin-table td { border: 1px solid #ddd; padding: 8px; text-align: center; }
    .admin-table th { background-color: #f2f2f2; }
    .btn-add { display: inline-block; padding: 10px 15px; background: #333; color: #fff; text-decoration: none; border-radius: 4px; margin-bottom: 15px;}
</style>
</head>
<body>
    <%@ include file="/WEB-INF/views/include/top.jsp" %>
    <main>
        <div class="admin-container">
            <h2 class="admin-title">상품 관리</h2>
            <a href="<c:url value='/admin/products/new'/>" class="btn-add">새 상품 등록</a>
            <table class="admin-table">
                <thead>
                    <tr>
                        <th>상품번호</th>
                        <th>이미지</th>
                        <th>상품명</th>
                        <th>가격</th>
                        <th>재고</th>
                        <th>관리</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${productList}">
                        <tr>
                            <td>${product.prdNo}</td>
                            <td><img src="<c:url value='/prd_images/${product.prdImg}'/>" alt="${product.prdName}" width="50"></td>
                            <td>${product.prdName}</td>
                            <td><fmt:formatNumber value="${product.prdPrice}" pattern="#,###" />원</td>
                            <td>${product.prdStock}</td>
                            <td>
                                <a href="<c:url value='/admin/products/edit/${product.prdNo}'/>">수정</a> | 
                                <a href="<c:url value='/admin/products/delete/${product.prdNo}'/>" 
                                	onclick="return confirm('정말로 삭제하시겠습니까?');">삭제</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </main>
    <%@ include file="/WEB-INF/views/include/bottom.jsp" %>
</body>
</html>