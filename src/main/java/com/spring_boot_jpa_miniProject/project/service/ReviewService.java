package com.spring_boot_jpa_miniProject.project.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	public void saveReview(String memId, Long prdNo, Long ordNo, String revContent, int revScore, MultipartFile file) {

		ReviewDTO review = new ReviewDTO();

		// 회원 연결
		MemberDTO member = new MemberDTO();
		member.setMemId(memId);
		review.setMember(member);

		// 상품 연결
		ProductDTO product = new ProductDTO();
		product.setPrdNo(prdNo);
		review.setProduct(product);

		// 나머지 정보 세팅
		review.setOrdNo(ordNo);
		review.setReviewContent(revContent);
		review.setReviewScore(revScore);

		// 파일 업로드 처리
		if (file != null && !file.isEmpty()) {
			try {
				// 저장할 경로
				String uploadDir = "C:/Users/Web-dev/workspaceBoot/review_images/";

				// 폴더가 실제로 존재하는지 확인하고, 없으면 생성하는 로직
				File dir = new File(uploadDir);
				if (!dir.exists()) {
					dir.mkdirs(); // 폴더가 없으면 생성
				}

				// 파일명 중복 방지 (UUID 사용)
				String originalFileName = file.getOriginalFilename();
				String savedFileName = UUID.randomUUID().toString() + "_" + originalFileName;

				// 파일 저장
				File dest = new File(uploadDir + savedFileName);
				file.transferTo(dest);

				// DTO에 파일명 세팅
				review.setReviewImg(savedFileName);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		reviewRepo.save(review);
	}

	// 특정 상품의 리뷰 목록 가져오기
	public List<ReviewDTO> getProductReviews(Long prdNo) {
		return reviewRepo.findAllByProductPrdNoOrderByReviewDateDesc(prdNo);
	}

	// 리뷰 삭제
	public void deleteReview(Long revNo) {
		reviewRepo.deleteById(revNo);
	}

	// 수정 페이지용: 리뷰 상세 조회
	public ReviewDTO getReviewDetail(Long revNo) {
		return reviewRepo.findById(revNo).orElse(null);
	}

	// 리뷰 수정 처리 (이미지 교체 로직 포함)
	public void updateReview(Long revNo, String revContent, int revScore, MultipartFile file) {
		ReviewDTO review = reviewRepo.findById(revNo).orElse(null);

		if (review != null) {
			// 내용과 별점 수정
			review.setReviewContent(revContent);
			review.setReviewScore(revScore);

			// 파일이 새로 업로드되었을 때만 이미지 교체
			if (file != null && !file.isEmpty()) {
				try {
					String uploadDir = "C:/Users/Web-dev/workspaceBoot/review_images/";

					String originalFileName = file.getOriginalFilename();
					String savedFileName = UUID.randomUUID().toString() + "_" + originalFileName;

					File dest = new File(uploadDir + savedFileName);
					file.transferTo(dest);

					// 새 파일명으로 교체
					review.setReviewImg(savedFileName);

				} catch (Exception e) {
					e.printStackTrace();
				}
			} 

			reviewRepo.save(review); // 변경감지 or 저장
		}
	}
}
