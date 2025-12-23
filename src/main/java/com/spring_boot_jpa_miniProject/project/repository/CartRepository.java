package com.spring_boot_jpa_miniProject.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.spring_boot_jpa_miniProject.project.dto.CartDTO;

public interface CartRepository extends JpaRepository<CartDTO, Long> {
	
	// 특정 회원의 장바구니 목록 조회
	List<CartDTO> findAllByMemberMemId(String memId);
	
	// 특정 회원이 특정 상품을 장바구니에 담았는지 확인
	CartDTO findByMemberMemIdAndProductPrdNo(String memId, Long prdNo);
	
	// 장바구니 비우기 (특정 회원)
	void deleteAllByMemberMemId(String memId);
}
