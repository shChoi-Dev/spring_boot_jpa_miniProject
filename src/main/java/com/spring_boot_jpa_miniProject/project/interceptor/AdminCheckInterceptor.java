package com.spring_boot_jpa_miniProject.project.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AdminCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 세션 가져오기
		HttpSession session = request.getSession();

		// 세션에서 권한(sRole) 정보 꺼내기
		String role = (String) session.getAttribute("sRole");

		// 로그인을 안 했거나(null), 관리자가 아니라면("ADMIN"이 아님) 차단
		if (role == null || !"ADMIN".equals(role)) {
			// 403 에러 발생 -> CustomErrorController가 잡아서 처리
	        response.sendError(HttpServletResponse.SC_FORBIDDEN); // 403 Forbidden
	        
			return false; // 컨트롤러로 진입하지 못하게 막음
		}

		return true;
	}
}
