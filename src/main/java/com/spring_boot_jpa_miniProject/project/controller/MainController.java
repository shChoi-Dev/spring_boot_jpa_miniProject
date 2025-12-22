package com.spring_boot_jpa_miniProject.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring_boot_jpa_miniProject.project.dto.ProductDTO;
import com.spring_boot_jpa_miniProject.project.dto.ReviewDTO;
import com.spring_boot_jpa_miniProject.project.service.ProductService;
import com.spring_boot_jpa_miniProject.project.service.ReviewService;

@Controller
public class MainController {
	
	@Autowired
	private ProductService prdService;
	
	@Autowired
    private ReviewService reviewService; // ReviewService 주입
	
	@GetMapping("/") 	// http://localhost:8080/ 요청을 받으면
    public String index(Model model) { // Model 객체를 파라미터로 받음
        // 상품 목록 가져오기
		List<ProductDTO> prdList = prdService.listAllProducts();
		model.addAttribute("prdList", prdList);
		
		// 베스트 리뷰 목록 가져오기
        List<ReviewDTO> reviewList = reviewService.getBestReviews();
        model.addAttribute("reviewList", reviewList);
		
		// 뷰(JSP)로 전달
        return "index";
    }
}
