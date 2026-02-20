package com.spring_boot_jpa_miniProject.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
		List<ProductDTO> productList = prdService.listProductsByCategory(prdCategory);
		
		String categoryName = "";
		switch (prdCategory) {
				case "carrier":
					categoryName = "캐리어";
					break;
				case "props":
					categoryName = "여행소품";
					break;
				case "safety":
					categoryName = "안전용품";
					break;
				default:
					categoryName = "상품 목록";
		}
		
		model.addAttribute("prdList", productList);
		model.addAttribute("category", prdCategory);	// 내부 로직용 영어
		model.addAttribute("categoryName", categoryName); // 화면 출력용 한글
		
		return "product/productList";
	}

	// 상품 검색 처리
	@GetMapping("/product/productSearch")
	public String productSearch(@RequestParam String keyword, Model model) {
		// 검색어로 상품 목록 조회
		List<ProductDTO> productList = prdService.productSearch(keyword);

		// 검색 결과 리스트 전달
		model.addAttribute("prdList", productList);

		// 안내 메시지용 검색어 전달
		model.addAttribute("searchKeyword", keyword);

		return "product/productList"; // 기존 상품 목록 페이지 재사용
	}
}
