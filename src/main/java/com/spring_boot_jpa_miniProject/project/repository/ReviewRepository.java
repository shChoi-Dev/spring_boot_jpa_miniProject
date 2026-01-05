package com.spring_boot_jpa_miniProject.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring_boot_jpa_miniProject.project.dto.ReviewDTO;

public interface ReviewRepository extends JpaRepository<ReviewDTO, Long> {
	// 메인 화면용 베스트 리뷰
	List<ReviewDTO> findTop2ByOrderByReviewDateDesc();
	
	// 특정 상품의 리뷰 목록 조회
	List<ReviewDTO> findAllByProductPrdNoOrderByReviewDateDesc(Long prdNo);
}
