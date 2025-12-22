package com.spring_boot_jpa_miniProject.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring_boot_jpa_miniProject.project.dto.ProductDTO;

public interface ProductRepository extends JpaRepository<ProductDTO, Integer> {
	// 카테고리 이름으로 상품 목록을 찾는 메소드 (JPA가 메소드 이름을 분석하여 자동 구현)
	List<ProductDTO> findByPrdCategory(String prdCategory);
}
