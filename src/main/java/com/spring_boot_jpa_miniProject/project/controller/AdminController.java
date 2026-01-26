package com.spring_boot_jpa_miniProject.project.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring_boot_jpa_miniProject.project.dto.OrderDTO;
import com.spring_boot_jpa_miniProject.project.dto.ProductDTO;
import com.spring_boot_jpa_miniProject.project.service.MemberService;
import com.spring_boot_jpa_miniProject.project.service.OrderService;
import com.spring_boot_jpa_miniProject.project.service.ProductService;

@Controller
public class AdminController {

	@Autowired
	private ProductService prdService;

	@Autowired
	private OrderService orderService; // OrderService 주입

	@Autowired
	private MemberService memberService;
	
	// /admin 으로 접속하면 /admin/main 으로 토스!
    @GetMapping("/admin")
    public String adminIndex() {
        return "redirect:/admin/main";
    }

	// 관리자용 상품 목록 페이지
	@GetMapping("/admin/products")
	public String adminProductList(Model model) {
		List<ProductDTO> productList = prdService.listAllProducts();
		model.addAttribute("productList", productList);
		return "admin/productList";
	}

	// 새 상품 등록 폼으로 이동
	@GetMapping("/admin/products/new")
	public String addProductForm() {
		return "admin/addProductFrom";
	}

	// 새 상품 등록 처리
	@PostMapping("/admin/products/new")
	public String addProduct(ProductDTO product, @RequestParam("file") MultipartFile file) {
		// 여기에 이미지 파일을 서버에 저장하고,
		// 파일명을 포함한 product 정보를 DB에 저장하는 서비스 로직을 호출

		try {
			// 서비스 메소드 호출하여 상품 정보와 파일 저장
			prdService.addProduct(product, file);
		} catch (IOException e) {
			// 파일 저장 중 오류 발생 시 예외 처리
			e.printStackTrace();
		}

		return "redirect:/admin/products"; // 등록 후 상품 관리 목록 페이지로 리다이렉트
	}

	// 상품 수정 폼으로 이동
	@GetMapping("/admin/products/edit/{prdNo}")
	public String editProductForm(@PathVariable Long prdNo, Model model) {
		// 1. 수정할 상품의 기존 정보를 DB에서 불러옴
		ProductDTO product = prdService.getProductDetail(prdNo);
		// 2. 모델에 담아서 뷰로 전달
		model.addAttribute("product", product);
		return "admin/editProductForm";
	}

	// 상품 수정 처리
	@PostMapping("/admin/products/edit")
	public String updateProduct(ProductDTO product, @RequestParam("file") MultipartFile file) {
		try {
			// 서비스에 상품 정보와 파일을 넘겨 업데이트 처리
			prdService.updateProduct(product, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/admin/products"; // 수정 후 상품 관리 목록으로 리다이렉트
	}

	// 상품 삭제 처리
	@GetMapping("admin/products/delete/{prdNo}")
	public String deleteProduct(@PathVariable Long prdNo) {
		prdService.deleteProduct(prdNo);
		return "redirect:/admin/products"; // 삭제 후 상품 관리 목록으로 리다이렉트
	}

	// 주문 관리 목록 페이지 이동
	@GetMapping("/admin/orders")
	public String adminOrderList(Model model) {
		// 모든 주문 가져오기
		List<OrderDTO> orderList = orderService.getAllOrders();
		model.addAttribute("orderList", orderList);
		return "admin/orderList";
	}

	// 주문 상태 변경 처리
	@PostMapping("/admin/orders/update")
	public String updateOrderStatus(@RequestParam Long ordNo, @RequestParam String status) {
		orderService.updateOrderStatus(ordNo, status);
		return "redirect:/admin/orders"; // 변경 후 목록으로 복귀
	}

	// 관리자 대시보드 (메인)
	@GetMapping("/admin/main")
	public String adminMain(Model model) {
		// 주요 지표 가져오기
		long memberCount = memberService.getMemberCount();
		long productCount = prdService.getProductCount();
		long orderCount = orderService.getOrderCount();
		long pendingCount = orderService.getPendingOrderCount(); // 처리해야 할 주문!

		// 모델에 담기
		model.addAttribute("memberCount", memberCount);
		model.addAttribute("productCount", productCount);
		model.addAttribute("orderCount", orderCount);
		model.addAttribute("pendingCount", pendingCount);

		return "admin/main";
	}
}
