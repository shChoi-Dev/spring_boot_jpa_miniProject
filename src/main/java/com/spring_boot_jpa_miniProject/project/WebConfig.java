package com.spring_boot_jpa_miniProject.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.spring_boot_jpa_miniProject.project.interceptor.AdminCheckInterceptor;
import com.spring_boot_jpa_miniProject.project.interceptor.LoginCheckInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	// 만든 인터셉터 주입
	@Autowired
	private AdminCheckInterceptor adminCheckInterceptor;
	
	// 로그인 체크 인터셉터 주입
    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 프로젝트 외부 경로 이미지 맵핑 설정
		// 맵핑 이름 : prd_images
		registry.addResourceHandler("/prd_images/**")
				.addResourceLocations("file:///C:/Users/Web-dev/workspaceBoot/prd_images/");
	}

	// 인터셉터 등록
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 관리자 체크 (기존 코드)
		registry.addInterceptor(adminCheckInterceptor)
				.addPathPatterns("/admin/**"); // /admin 으로 시작하는 모든 경로 검사
		
		// 로그인 체크 (일반 회원용)
        registry.addInterceptor(loginCheckInterceptor)
                .addPathPatterns("/member/myPage")      // 마이페이지
                .addPathPatterns("/member/updateForm")  // 정보수정
                .addPathPatterns("/member/pwdCheck")    // 비번확인
                .addPathPatterns("/cart/**")            // 장바구니 관련 전체
                .addPathPatterns("/order/**")          	// 주문 관련 전체
                .addPathPatterns("/review/write/**"); 	// 리뷰 작성 전체
    }
}
