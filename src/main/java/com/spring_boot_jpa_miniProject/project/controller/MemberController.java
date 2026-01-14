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
	public String login(@RequestParam String memId, @RequestParam String memPwd, HttpSession session) {

		// loginCheck의 결과로 MemberDTO 객체를 받음
		MemberDTO member = memService.loginCheck(memId, memPwd);

		if (member != null) { // 로그인 성공 시
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

		// 회원 정보 조회 (이름, 연락처 등 표시용)
		MemberDTO member = memService.getMemberInfo(memId);

		// 주문 내역 조회 (OrderService에서 만든 메서드 호출)
		List<OrderDTO> orderList = orderService.getMyOrderList(memId);

		// 화면(JSP)으로 데이터 전달
		model.addAttribute("member", member);
		model.addAttribute("orderList", orderList);

		return "member/myPage";
	}

	// 비밀번호 확인 폼 이동 (내 정보 수정 전 본인 확인)
	@GetMapping("/member/pwdCheck")
	public String pwdCheckForm() {
		return "member/pwdCheck";
	}

	// 비밀번호 확인 처리
	@PostMapping("/member/pwdCheck")
	public String pwdCheck(@RequestParam String memPwd, HttpSession session, Model model) {
		String memId = (String) session.getAttribute("sid");

		// 서비스에서 비밀번호 일치 여부 확인
		boolean result = memService.checkPassword(memId, memPwd);

		if (result) {
			return "redirect:/member/updateForm"; // 일치하면 수정 페이지로 이동
		} else {
			model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
			model.addAttribute("url", "/member/pwdCheck"); // 다시 비밀번호 확인 페이지로
			return "common/alert";
		}
	}

	// 회원 정보 수정 폼 이동
	@GetMapping("/member/updateForm")
	public String updateForm(HttpSession session, Model model) {
		String memId = (String) session.getAttribute("sid");
		MemberDTO member = memService.getMemberInfo(memId); // 현재 내 정보 가져오기
		model.addAttribute("member", member);
		return "member/updateForm";
	}

	// 회원 정보 수정 처리
	@PostMapping("/member/update")
	public String updateMember(MemberDTO member, HttpSession session, Model model) {
		// 세션에서 아이디를 가져와서 DTO에 세팅 (보안상 안전)
		String memId = (String) session.getAttribute("sid");
		member.setMemId(memId);

		memService.updateMember(member);

		model.addAttribute("msg", "회원 정보가 수정되었습니다.");
		model.addAttribute("url", "/member/myPage");
		return "common/alert";
	}

	// 회원 탈퇴 처리
	@PostMapping("/member/withdraw")
	public String withdrawMember(HttpSession session, Model model) {
		String memId = (String) session.getAttribute("sid");

		// 서비스의 탈퇴 메서드 호출 (상태값을 "N"으로 변경)
		memService.withdrawMember(memId);

		session.invalidate(); // 세션 삭제 (로그아웃)

		model.addAttribute("msg", "회원 탈퇴가 완료되었습니다. 그동안 이용해주셔서 감사합니다.");
		model.addAttribute("url", "/"); // 메인으로 이동
		return "common/alert";
	}

}
