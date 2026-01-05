package com.spring_boot_jpa_miniProject.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring_boot_jpa_miniProject.project.dto.ProductDTO;
import com.spring_boot_jpa_miniProject.project.dto.ReviewDTO;
import com.spring_boot_jpa_miniProject.project.service.ProductService;
import com.spring_boot_jpa_miniProject.project.service.ReviewService;


@Controller
public class ProductController {
	
	@Autowired
	private ProductService prdService;
	
	@Autowired
    private ReviewService revService;
	
	// 상품 상세 정보 페이지
    @GetMapping("/product/detail/{prdNo}")
    public String getProductDetail(@PathVariable Long prdNo, Model model) {
    	
        // prdNo에 해당하는 상품 정보를 서비스로부터 받아옴
        ProductDTO product = prdService.getProductDetail(prdNo);
        
        // 해당 상품의 리뷰 목록 가져오기
        List<ReviewDTO> reviewList = revService.getProductReviews(prdNo);
        
        // 화면에 데이터 전달
        model.addAttribute("product", product);
        model.addAttribute("reviewList", reviewList); // 리뷰 목록 전달
        
        // 뷰 페이지로 전달
        return "product/productDetail";
    }
    
    // 카테고리별 상품 목록 페이지
    @GetMapping("/product/category/{prdCategory}")
    public String getProductsByCategory(@PathVariable String prdCategory, Model model) {
        // 이 메소드가 존재하지 않거나 잘못 작성되었을 가능성이 높습니다.
        List<ProductDTO> productList = prdService.listProductsByCategory(prdCategory);
        model.addAttribute("prdList", productList);
        model.addAttribute("category", prdCategory);
        return "product/productList";
    }
}
