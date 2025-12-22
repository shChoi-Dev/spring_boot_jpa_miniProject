<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header>
        <div class="header-container">
            <h1><a href="<c:url value='/'/>">TRVR</a></h1>

            <nav>
                <ul>
					<li><a href="<c:url value='/product/category/carrier'/>">캐리어</a></li>
        			<li><a href="<c:url value='/product/category/props'/>">여행소품</a></li>
        			<li><a href="<c:url value='/product/category/safety'/>">안전용품</a></li>
                </ul>
            </nav>

            <div class="user-menu">
                <ul>
	                <%-- 로그아웃 상태일 때 보여줄 메뉴 --%>
	                <c:if test="${empty sessionScope.sid}">
	                    <li><a href="<c:url value='/member/loginForm'/>">로그인</a></li>
	                    <li><a href="<c:url value='/member/joinForm'/>">회원가입</a></li>
	                </c:if>
                
                	<%-- 로그인 상태일 때 보여줄 메뉴 --%>
                	<c:if test="${not empty sessionScope.sid}">
	                    <li><span style="font-weight: bold;">${sessionScope.sid}님 환영합니다!</a></li>
	                    <li><a href="/member/logout">로그아웃</a></li>
	                    <li><a href="/mypage">마이페이지</a></li>
	                    <li><a href="/cart">장바구니</a></li>
	                    
	                    <%-- 역할(sRole)이 'ADMIN'일 경우에만 관리자 페이지 링크를 보여줌 --%>
            			<c:if test="${sessionScope.sRole == 'ADMIN'}">
                			<li><a href="<c:url value='/admin/products'/>">관리자 페이지</a></li>
            			</c:if>
                    </c:if>
                </ul>
            </div>
        </div>
    </header>