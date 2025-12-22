<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TRVR - 로그인</title>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
</head>
<body>
    <%@ include file="/WEB-INF/views/include/top.jsp" %>

    <main>
        <div class="form-container">
            <h2 class="form-title">로그인</h2>
            <form action="<c:url value='/member/login'/>" method="post">
                <div class="form-group">
                    <label class="form-label" for="memId">아이디</label>
                    <input type="text" id="memId" name="memId" class="form-input">
                </div>
                <div class="form-group">
                    <label class="form-label" for="memPwd">비밀번호</label>
                    <input type="password" id="memPwd" name="memPwd" class="form-input">
                </div>
                <input type="submit" value="로그인" class="form-button">
            </form>
        </div>
    </main>
    
    <%@ include file="/WEB-INF/views/include/bottom.jsp" %>
</body>
</html>