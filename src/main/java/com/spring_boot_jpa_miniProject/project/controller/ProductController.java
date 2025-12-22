package com.spring_boot_jpa_miniProject.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring_boot_jpa_miniProject.project.dto.ProductDTO;
import com.spring_boot_jpa_miniProject.project.service.ProductService;


@Controller
public class ProductController {
	
	@Autowired
	private ProductService prdService;
	
	// 상품 상세 정보 페이지
    @GetMapping("/product/detail/{prdNo}")
    public String getProductDetail(@PathVariable int prdNo, Model model) {
        // 1. prdNo에 해당하는 상품 정보를 서비스로부터 받아옴
        ProductDTO product = prdService.getProductDetail(prdNo);
        // 2. 모델에 상품 정보를 product에 담음
        model.addAttribute("product", product);
        // 3. 뷰 페이지로 전달
        return "product/productDetail"; // /WEB-INF/views/product/productDetail.jsp
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
