package com.spring_boot_jpa_miniProject.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring_boot_jpa_miniProject.project.dto.ProductDTO;

public interface ProductRepository extends JpaRepository<ProductDTO, Long> {
	// 카테고리 이름으로 상품 목록을 찾는 메소드 (JPA가 메소드 이름을 분석하여 자동 구현)
	List<ProductDTO> findByPrdCategory(String prdCategory);

	// 상품 이름에 검색어가 포함된 상품 찾기 (LIKE %keyword%)
	List<ProductDTO> findByPrdNameContaining(String keyword);

	// 상품 번호(PrdNo) 역순(최신순)으로 상위 4개만 조회
	List<ProductDTO> findTop4ByOrderByPrdNoDesc();
	
	// 인기 상품 4개
	List<ProductDTO> findTop4ByOrderByPrdPriceDesc();
}
