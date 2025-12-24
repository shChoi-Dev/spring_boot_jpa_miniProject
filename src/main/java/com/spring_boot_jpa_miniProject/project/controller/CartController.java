package com.spring_boot_jpa_miniProject.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring_boot_jpa_miniProject.project.dto.CartDTO;
import com.spring_boot_jpa_miniProject.project.service.CartService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    // 장바구니 담기
    @ResponseBody
    @PostMapping("/cart/insert")
    public String insertCart(@RequestParam Long prdNo,
                             @RequestParam int cartQty,
                             HttpSession session) {
        
        // 로그인 확인
        String memId = (String) session.getAttribute("sid");
        if (memId == null) {
            return "fail";
        }

        // 서비스 호출하여 담기
        cartService.insertCart(memId, prdNo, cartQty);
        
        return "success";
    }

    // 장바구니 목록 페이지 이동
    @GetMapping("/cart")
    public String cartList(HttpSession session, Model model) {
        String memId = (String) session.getAttribute("sid");
        
        // 비로그인 시 로그인 페이지로
        if (memId == null) {
            return "redirect:/member/loginForm";
        }

        List<CartDTO> cartList = cartService.listCart(memId);
        
        // 장바구니 총 금액 계산
        int totalPrice = 0;
        for(CartDTO cart : cartList) {
            totalPrice += cart.getProduct().getPrdPrice() * cart.getCartQty();
        }

        model.addAttribute("cartList", cartList);
        model.addAttribute("totalPrice", totalPrice);
        
        return "cart/cartList";
    }
    
    // 장바구니 개별 삭제
    @GetMapping("/cart/delete")
    public String deleteCart(@RequestParam Long cartNo) {
        cartService.deleteCart(cartNo);
        return "redirect:/cart";
    }
}