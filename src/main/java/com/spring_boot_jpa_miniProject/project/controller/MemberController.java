package com.spring_boot_jpa_miniProject.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring_boot_jpa_miniProject.project.dto.MemberDTO;
import com.spring_boot_jpa_miniProject.project.dto.OrderDTO;
import com.spring_boot_jpa_miniProject.project.service.MemberService;
import com.spring_boot_jpa_miniProject.project.service.OrderService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memService;
	
	@Autowired
    private OrderService orderService;
	
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
	
	// 마이페이지 이동 (내 주문 내역 조회)
    @GetMapping("/member/myPage")
    public String myPage(HttpSession session, Model model) {
        // 세션에서 로그인된 아이디 가져오기
        String memId = (String) session.getAttribute("sid");
        
        // 로그인이 안 되어 있다면 로그인 페이지로 보냄
        if(memId == null) {
            return "redirect:/member/loginForm";
        }
        
        // 회원 정보 조회 (이름, 연락처 등 표시용)
        MemberDTO member = memService.getMemberInfo(memId);
        
        // 주문 내역 조회 (OrderService에서 만든 메서드 호출)
        List<OrderDTO> orderList = orderService.getMyOrderList(memId);
        
        // 화면(JSP)으로 데이터 전달
        model.addAttribute("member", member);
        model.addAttribute("orderList", orderList);
        
        return "member/myPage";
    }
}

