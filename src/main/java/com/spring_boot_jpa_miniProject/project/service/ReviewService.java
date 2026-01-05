package com.spring_boot_jpa_miniProject.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring_boot_jpa_miniProject.project.dto.MemberDTO;
import com.spring_boot_jpa_miniProject.project.dto.ProductDTO;
import com.spring_boot_jpa_miniProject.project.dto.ReviewDTO;
import com.spring_boot_jpa_miniProject.project.repository.MemberRepository;
import com.spring_boot_jpa_miniProject.project.repository.ProductRepository;
import com.spring_boot_jpa_miniProject.project.repository.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepo;

	@Autowired
	private MemberRepository memRepo;

	@Autowired
	private ProductRepository prdRepo;
	
	// 메인 화면 베스트 리뷰 조회
    public List<ReviewDTO> getBestReviews() {
        return reviewRepo.findTop2ByOrderByReviewDateDesc();
    }

	// 리뷰 저장
	public void saveReview(String memId, Long prdNo, Long ordNo, String content, int score) {
		MemberDTO member = memRepo.findById(memId).orElse(null);
		ProductDTO product = prdRepo.findById(prdNo).orElse(null);

		if (member != null && product != null) {
			ReviewDTO review = new ReviewDTO();
			review.setMember(member);
			review.setProduct(product);
			review.setOrdNo(ordNo);
			review.setReviewContent(content);
			review.setReviewScore(score);

			reviewRepo.save(review);
		}
	}

	// 특정 상품의 리뷰 목록 가져오기
	public List<ReviewDTO> getProductReviews(Long prdNo) {
		return reviewRepo.findAllByProductPrdNoOrderByReviewDateDesc(prdNo);
	}
}
