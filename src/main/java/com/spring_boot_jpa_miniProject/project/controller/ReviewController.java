package com.spring_boot_jpa_miniProject.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring_boot_jpa_miniProject.project.dto.ProductDTO;
import com.spring_boot_jpa_miniProject.project.service.ProductService;
import com.spring_boot_jpa_miniProject.project.service.ReviewService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReviewController {
	
	@Autowired
    private ReviewService reviewService;
    
    @Autowired
    private ProductService productService; // 상품 정보를 보여주기 위해 필요

    // 리뷰 작성 페이지 이동
    @GetMapping("/review/writeForm")
    public String writeForm(@RequestParam Long prdNo, 
                            @RequestParam Long ordNo, 
                            HttpSession session,
                            Model model) {
        
        // 어떤 상품에 대한 리뷰인지 보여주기 위해 상품 정보 가져오기
        ProductDTO product = productService.getProductDetail(prdNo);
        
        model.addAttribute("product", product);
        model.addAttribute("ordNo", ordNo);
        
        return "review/reviewWrite";
    }

    // 리뷰 저장 처리
    @PostMapping("/review/save")
    public String reviewSave(@RequestParam Long prdNo,
                             @RequestParam Long ordNo,
                             @RequestParam String revContent,
                             @RequestParam int revScore,
                             HttpSession session) {
        
    	// 로그인한 사용자 아이디 가져오기
        String memId = (String) session.getAttribute("sid");
        
        // 서비스 호출하여 저장
        reviewService.saveReview(memId, prdNo, ordNo, revContent, revScore);
        
        // 저장 후 다시 주문 상세 페이지로 돌아가거나, 상품 상세 페이지로 이동
        return "redirect:/order/detail?ordNo=" + ordNo;
    }
}
