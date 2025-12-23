package com.spring_boot_jpa_miniProject.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring_boot_jpa_miniProject.project.dto.CartDTO;
import com.spring_boot_jpa_miniProject.project.dto.MemberDTO;
import com.spring_boot_jpa_miniProject.project.dto.ProductDTO;
import com.spring_boot_jpa_miniProject.project.repository.CartRepository;
import com.spring_boot_jpa_miniProject.project.repository.MemberRepository;
import com.spring_boot_jpa_miniProject.project.repository.ProductRepository;

@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private MemberRepository memRepo;
	
	@Autowired
	private ProductRepository prdRepo;
	
	// 장바구니 추가 (있으면 수량 증가, 없으면 신규 저장)
	public void insertCart(String memId, Long prdNo, int cartQty) {
		
		// 이미 장바구니에 담긴 상품인지 확인 
		CartDTO cartItem = cartRepo.findByMemberMemIdAndProductPrdNo(memId, prdNo);
		
		if (cartItem != null) {
			// 이미 있으면 수량만 증가 (기존 수량 + 새 수량)
			cartItem.setCartQty(cartItem.getCartQty() + cartQty);
			cartRepo.save(cartItem);
		} else {
			// 없으면 새로 생성
			MemberDTO member = memRepo.findById(memId).orElse(null);
			ProductDTO product = prdRepo.findById(prdNo).orElse(null); 
			
			if(member != null && product != null) {
				CartDTO newCart = new CartDTO();
				newCart.setMember(member);
				newCart.setProduct(product);
				newCart.setCartQty(cartQty);
				cartRepo.save(newCart);
			}
		}
	}
	
	// 장바구니 목록 조회
	public List<CartDTO> listCart(String memId) {
		return cartRepo.findAllByMemberMemId(memId);
	}
	
	// 장바구니 삭제
	public void deleteCart(Long cartNo) {
		cartRepo.deleteById(cartNo);
	}
	
	// 장바구니 전체 비우기
	public void deleteAllCart(String memId) {
		cartRepo.deleteAllByMemberMemId(memId);
	}
}
