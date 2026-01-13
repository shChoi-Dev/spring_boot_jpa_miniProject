package com.spring_boot_jpa_miniProject.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.spring_boot_jpa_miniProject.project.interceptor.AdminCheckInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	// 만든 인터셉터 주입
	@Autowired
	private AdminCheckInterceptor adminCheckInterceptor;

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
		registry.addInterceptor(adminCheckInterceptor).addPathPatterns("/admin/**"); // /admin 으로 시작하는 모든 경로 검사
	}
}
