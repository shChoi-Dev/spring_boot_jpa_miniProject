<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>TRVR - 회원가입</title>
    <%@ include file="/WEB-INF/views/include/head.jsp" %>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/top.jsp" %>
	
	<main>
        <div class="form-container">
            <h2 class="form-title">회원가입</h2>
            <form action="<c:url value='/member/join'/>" method="post">
                <div class="form-group">
                    <label class="form-label" for="memId">아이디</label>
                    <input type="text" id="memId" name="memId" class="form-input">
                </div>
                <div class="form-group">
                    <label class="form-label" for="memPwd">비밀번호</label>
                    <input type="password" id="memPwd" name="memPwd" class="form-input">
                </div>
                <div class="form-group">
                    <label class="form-label" for="memName">이름</label>
                    <input type="text" id="memName" name="memName" class="form-input">
                </div>
                <div class="form-group">
                    <label class="form-label" for="memEmail">이메일</label>
                    <input type="email" id="memEmail" name="memEmail" class="form-input">
                </div>
                 <div class="form-group">
                    <label class="form-label" for="memHp">연락처</label>
                    <input type="text" id="memHp" name="memHp" class="form-input">
                </div>
                <div class="form-group">
                    <label class="form-label" for="memZipcode">우편번호</label>
                    <input type="text" id="memZipcode" name="memZipcode" class="form-input">
                </div>
                <div class="form-group">
                    <label class="form-label" for="memAddress1">주소</label>
                    <input type="text" id="memAddress1" name="memAddress1" class="form-input">
                </div>
                <div class="form-group">
                    <label class="form-label" for="memAddress2">상세주소</label>
                    <input type="text" id="memAddress2" name="memAddress2" class="form-input">
                </div>
                <input type="submit" value="가입하기" class="form-button">
            </form>
        </div>
    </main>

    <%@ include file="/WEB-INF/views/include/bottom.jsp" %>
</body>
</html>