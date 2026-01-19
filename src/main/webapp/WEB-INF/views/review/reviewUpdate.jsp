<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TRVR - 리뷰 수정</title>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<style>
.review-container {
    width: 600px;
    margin: 50px auto;
    padding: 30px;
    border: 1px solid #ddd;
    border-radius: 10px;
}

.prd-info {
    display: flex;
    align-items: center;
    margin-bottom: 20px;
    padding-bottom: 20px;
    border-bottom: 1px solid #eee;
}

.prd-img {
    width: 80px;
    height: 80px;
    object-fit: cover;
    border-radius: 5px;
    margin-right: 15px;
}

.prd-name {
    font-size: 1.2rem;
    font-weight: bold;
}

/* 별점 스타일 */
.star-rating {
    display: flex;
    flex-direction: row-reverse;
    justify-content: center;
    margin: 20px 0;
}

.star-rating input {
    display: none;
}

.star-rating label {
    font-size: 2rem;
    color: #ddd;
    cursor: pointer;
    padding: 0 5px;
    transition: color 0.2s;
}

.star-rating label:hover, .star-rating label:hover ~ label, .star-rating input:checked 
    ~ label {
    color: #f1c40f;
}

.review-text {
    width: 100%;
    height: 150px;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    resize: none;
    margin-bottom: 20px;
    font-family: inherit;
    box-sizing: border-box;
}

.btn-submit {
    width: 100%;
    padding: 15px;
    background-color: #333;
    color: white;
    border: none;
    border-radius: 5px;
    font-size: 1.1rem;
    cursor: pointer;
    box-sizing: border-box;
}

.btn-submit:hover {
    background-color: #555;
}

/* 파일 업로드 영역 추가 스타일 */
.form-group {
    margin-bottom: 20px;
}
</style>
</head>
<body>
    <%@ include file="/WEB-INF/views/include/top.jsp"%>

    <main>
        <div class="review-container">
            <h2>✍ 리뷰 수정</h2>

            <div class="prd-info">
                <img src="<c:url value='/prd_images/${review.product.prdImg}'/>"
                    class="prd-img">
                <div class="prd-name">${review.product.prdName}</div>
            </div>

            <form action="<c:url value='/review/update'/>" method="post"
                enctype="multipart/form-data">
                
                <input type="hidden" name="revNo" value="${review.reviewNo}">
                <input type="hidden" name="prdNo" value="${review.product.prdNo}">

                <div style="text-align: center; margin-bottom: 5px; color: #777;">
                    별점을 수정하시겠어요?
                </div>
                
                <div class="star-rating">
                    <input type="radio" id="star5" name="revScore" value="5" ${review.reviewScore == 5 ? 'checked' : ''}><label for="star5">★</label>
                    <input type="radio" id="star4" name="revScore" value="4" ${review.reviewScore == 4 ? 'checked' : ''}><label for="star4">★</label>
                    <input type="radio" id="star3" name="revScore" value="3" ${review.reviewScore == 3 ? 'checked' : ''}><label for="star3">★</label>
                    <input type="radio" id="star2" name="revScore" value="2" ${review.reviewScore == 2 ? 'checked' : ''}><label for="star2">★</label>
                    <input type="radio" id="star1" name="revScore" value="1" ${review.reviewScore == 1 ? 'checked' : ''}><label for="star1">★</label>
                </div>

                <div class="form-group">
                    <label style="display:block; margin-bottom:5px; font-weight:bold;">사진 수정</label>
                    
                    <c:if test="${not empty review.reviewImg}">
                        <div style="margin-bottom: 10px; padding: 10px; background: #f9f9f9; border-radius: 5px; display: flex; align-items: center;">
                            <img src="<c:url value='/review_images/${review.reviewImg}'/>" width="60" height="60" style="object-fit: cover; border-radius: 4px; margin-right: 10px;">
                            <span style="font-size: 0.9rem; color: #666;">현재 등록된 사진</span>
                        </div>
                    </c:if>
                    
                    <input type="file" name="file" class="form-control" accept="image/*" style="width: 100%;">
                    <div style="font-size: 0.8rem; color: #888; margin-top: 5px;">
                        * 새로운 사진을 선택하면 기존 사진이 변경됩니다.
                    </div>
                </div>

                <textarea name="revContent" class="review-text" required>${review.reviewContent}</textarea>

                <button type="submit" class="btn-submit">리뷰 수정완료</button>
            </form>
        </div>
    </main>

    <%@ include file="/WEB-INF/views/include/bottom.jsp"%>
</body>
</html>