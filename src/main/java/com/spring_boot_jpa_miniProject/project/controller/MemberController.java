package com.spring_boot_jpa_miniProject.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring_boot_jpa_miniProject.project.dto.MemberDTO;
import com.spring_boot_jpa_miniProject.project.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memService;
	
	// 회원가입 폼으로 이동
	@GetMapping("/member/joinForm")
	public String joinForm() {
		return "member/joinForm";
	}
	
	// 회원가입 처리
	@PostMapping("/member/join")
	public String join(MemberDTO member) {
		memService.saveMember(member);
		return "redirect:/member/loginForm"; // 회원가입 후 로그인 폼으로 리다이렉트
	}
	
	// 로그인 폼으로 이동
	@GetMapping("/member/loginForm")
	public String loginForm() {
	    return "member/loginForm"; 
	}
	
	// 로그인 처리
	@PostMapping("/member/login")
	public String login(@RequestParam String memId,
						@RequestParam String memPwd,
						HttpSession session) {
		
		// loginCheck의 결과로 MemberDTO 객체를 받음
	    MemberDTO member = memService.loginCheck(memId, memPwd);
		
		if(member != null) { // 로그인 성공 시
			// 세션에 아이디, 이름, 역할을 각각 저장
			session.setAttribute("sid", member.getMemId());
	        session.setAttribute("sName", member.getMemName());
	        session.setAttribute("sRole", member.getMemRole());
			return "redirect:/"; // 메인 페이지로 리다이렉트
		} else { // 로그인 실패 시
			return "redirect:/member/loginForm"; // 로그인 폼으로 다시 리다이렉트
		}
	}
	
	// 로그아웃 처리
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 세션 무효화
		return "redirect:/"; // 메인 페이지로 리다이렉트
	}
}

