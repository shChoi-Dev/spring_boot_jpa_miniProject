<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>\
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TRVR - 리뷰 작성</title>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<style>
    .review-container { width: 600px; margin: 50px auto; padding: 30px; border: 1px solid #ddd; border-radius: 10px; }
    .prd-info { display: flex; align-items: center; margin-bottom: 20px; padding-bottom: 20px; border-bottom: 1px solid #eee; }
    .prd-img { width: 80px; height: 80px; object-fit: cover; border-radius: 5px; margin-right: 15px; }
    .prd-name { font-size: 1.2rem; font-weight: bold; }
    
    /* 별점 스타일 (라디오 버튼 숨기고 별 모양 보여주기) */
    .star-rating { display: flex; flex-direction: row-reverse; justify-content: center; margin: 20px 0; }
    .star-rating input { display: none; }
    .star-rating label { font-size: 2rem; color: #ddd; cursor: pointer; padding: 0 5px; transition: color 0.2s; }
    .star-rating label:hover,
    .star-rating label:hover ~ label,
    .star-rating input:checked ~ label { color: #f1c40f; } /* 선택되거나 마우스 올리면 노란색 */
    
    .review-text { width: 100%; height: 150px; padding: 10px; border: 1px solid #ccc; border-radius: 5px; resize: none; margin-bottom: 20px; font-family: inherit; }
    .btn-submit { width: 100%; padding: 15px; background-color: #333; color: white; border: none; border-radius: 5px; font-size: 1.1rem; cursor: pointer; }
    .btn-submit:hover { background-color: #555; }
</style>
</head>
<body>
    <%@ include file="/WEB-INF/views/include/top.jsp" %>

    <main>
        <div class="review-container">
            <h2>✍ 리뷰 작성</h2>
            
            <div class="prd-info">
                <img src="<c:url value='/prd_images/${product.prdImg}'/>" class="prd-img">
                <div class="prd-name">${product.prdName}</div>
            </div>
            
            <form action="<c:url value='/review/save'/>" method="post">
                <input type="hidden" name="prdNo" value="${product.prdNo}">
                <input type="hidden" name="ordNo" value="${ordNo}">
                
                <div style="text-align: center; margin-bottom: 5px; color: #777;">상품은 만족하셨나요?</div>
                <div class="star-rating">
                    <input type="radio" id="star5" name="revScore" value="5" checked><label for="star5">★</label>
                    <input type="radio" id="star4" name="revScore" value="4"><label for="star4">★</label>
                    <input type="radio" id="star3" name="revScore" value="3"><label for="star3">★</label>
                    <input type="radio" id="star2" name="revScore" value="2"><label for="star2">★</label>
                    <input type="radio" id="star1" name="revScore" value="1"><label for="star1">★</label>
                </div>
                
                <textarea name="revContent" class="review-text" placeholder="솔직한 리뷰를 남겨주세요." required></textarea>
                
                <button type="submit" class="btn-submit">리뷰 등록하기</button>
            </form>
        </div>
    </main>

    <%@ include file="/WEB-INF/views/include/bottom.jsp" %>
</body>
</html>