package com.spring_boot_jpa_miniProject.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity 
public class WebSecurityConfig {
  @Bean
  public PasswordEncoder getPasswordEncoder() {
	  return new BCryptPasswordEncoder();
	  // BCrypt라는 해시 함수를 이용하여 패스워드를 암호화는 구현체
  }
  
  // 다음 코드 추가하지 않으면 스프링 기본 로그인 화면 출력 
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	  http
	  	.csrf(csrf -> csrf.disable())
	  	.formLogin(formLogin -> formLogin.disable())
        .headers(headerConfig -> 
        	headerConfig.frameOptions(frameOptionsConfig ->
        							frameOptionsConfig.disable()));

       return http.build();
  }
}
