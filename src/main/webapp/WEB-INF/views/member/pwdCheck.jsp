<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 확인</title>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/top.jsp"%>

	<main>
        <div class="form-container">
            <h2 class="form-title">🔒 본인 확인</h2>
            <p style="text-align: center; margin-bottom: 20px; color: #666;">
                개인정보 보호를 위해 비밀번호를 입력해주세요.
            </p>
            
            <form action="<c:url value='/member/pwdCheck'/>" method="post">
                <div class="form-group">
                    <label class="form-label" for="memPwd">비밀번호</label>
                    <input type="password" id="memPwd" name="memPwd" class="form-input" placeholder="비밀번호 입력" required>
                </div>
                
                <button type="submit" class="form-button">확인</button>
            </form>
        </div>
    </main>

	<%@ include file="/WEB-INF/views/include/bottom.jsp"%>
</body>
</html>