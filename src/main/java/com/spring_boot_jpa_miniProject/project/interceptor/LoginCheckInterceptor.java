package com.spring_boot_jpa_miniProject.project.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 세션 가져오기
        HttpSession session = request.getSession();
        
        // 세션에 아이디가 없으면 로그인 안 한 상태
        if(session.getAttribute("sid") == null) {
            
            // 로그인 페이지로 보내기
            response.sendRedirect("/member/loginForm");
            
            return false; // 컨트롤러 실행 막기
        }
        
        return true;
    }
}
