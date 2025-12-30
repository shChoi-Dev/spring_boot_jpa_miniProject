package com.spring_boot_jpa_miniProject.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring_boot_jpa_miniProject.project.dto.CartDTO;
import com.spring_boot_jpa_miniProject.project.dto.MemberDTO;
import com.spring_boot_jpa_miniProject.project.dto.OrderDTO;
import com.spring_boot_jpa_miniProject.project.service.MemberService;
import com.spring_boot_jpa_miniProject.project.service.OrderService;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private MemberService memberService;

	// 주문서 작성 페이지로 이동
	@PostMapping("/order/form")
	public String orderForm(@RequestParam List<Long> cartNos, HttpSession session, Model model) {
		String memId = (String) session.getAttribute("sid");

		// 선택된 상품 리스트 조회
		List<CartDTO> cartList = orderService.getCartListForOrder(cartNos);

		// 총 주문 금액 계산
		int totalPrice = 0;
		for (CartDTO cart : cartList) {
			totalPrice += cart.getProduct().getPrdPrice() * cart.getCartQty();
		}

		// 주문자 정보 가져오기 (배송지에 자동 입력용)
		MemberDTO member = memberService.getMemberInfo(memId);

		model.addAttribute("cartList", cartList);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("member", member);

		return "order/orderForm";
	}

	// 주문 완료 처리
	@PostMapping("/order/insert")
	public String orderInsert(@ModelAttribute OrderDTO order, @RequestParam List<Long> cartNos, HttpSession session) {
		String memId = (String) session.getAttribute("sid");

		// 서비스 호출 (주문 저장 -> 상세 저장 -> 장바구니 삭제)
		orderService.insertOrder(order, memId, cartNos);

		return "redirect:/"; // 메인으로 이동
	}
}
