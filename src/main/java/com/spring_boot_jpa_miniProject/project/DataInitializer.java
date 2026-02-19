package com.spring_boot_jpa_miniProject.project;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.spring_boot_jpa_miniProject.project.dto.MemberDTO;
import com.spring_boot_jpa_miniProject.project.dto.ProductDTO;
import com.spring_boot_jpa_miniProject.project.dto.ReviewDTO;
import com.spring_boot_jpa_miniProject.project.repository.MemberRepository;
import com.spring_boot_jpa_miniProject.project.repository.ProductRepository;
import com.spring_boot_jpa_miniProject.project.repository.ReviewRepository;

@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired
	private MemberRepository memberRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired 
	private ProductRepository productRepo;
	
	@Autowired 
	private ReviewRepository reviewRepo;

	
	@Override
	public void run(String... args) throws Exception {
		// 회원 샘플 생성
		createMembers();
		
		// 상품 샘플 생성
		createProducts();
		
		// 리뷰 샘플 생성
		createReviews();
	}
	
	private void createMembers() {
		if (memberRepo.findById("admin").isEmpty()) {
			MemberDTO admin = new MemberDTO();
			admin.setMemId("admin");
			admin.setMemPwd(passwordEncoder.encode("1234"));
			admin.setMemName("관리자");
			admin.setMemEmail("admin@trvr.com");
			admin.setMemHp("010-0000-0000");
			admin.setMemRole("ADMIN");
			memberRepo.save(admin);
			System.out.println(">>> [초기화] 관리자 계정(admin) 생성 완료!");
		}

		if (memberRepo.findById("user1").isEmpty()) {
			MemberDTO user = new MemberDTO();
			user.setMemId("user1");
			user.setMemPwd(passwordEncoder.encode("1234"));
			user.setMemName("홍길동");
			user.setMemEmail("user1@trvr.com");
			user.setMemHp("010-1234-5678");
			user.setMemRole("USER");
			memberRepo.save(user);
			System.out.println(">>> [초기화] 일반 회원(user1) 생성 완료!");
		}
	}

	private void createProducts() {
		// 상품 테이블이 비어있을 때만 샘플 4개 추가
		if (productRepo.count() == 0) {
			ProductDTO p1 = new ProductDTO();
			p1.setPrdName("여행용 캐리어 (24인치)");
			p1.setPrdPrice(150000);
			p1.setPrdStock(100);
			p1.setPrdDesc("튼튼하고 가벼운 여행용 캐리어입니다.");
			p1.setPrdCategory("bag");
			p1.setPrdImg("carrier1.jpg");
			productRepo.save(p1);

			ProductDTO p2 = new ProductDTO();
			p2.setPrdName("여행용 백팩");
			p2.setPrdPrice(85000);
			p2.setPrdStock(50);
			p2.setPrdDesc("수납 공간이 많은 실용적인 백팩입니다.");
			p2.setPrdCategory("bag");
			p2.setPrdImg("backpack1.jpg");
			productRepo.save(p2);
			
			ProductDTO p3 = new ProductDTO();
			p3.setPrdName("여행용 멀티 어댑터");
			p3.setPrdPrice(12000);
			p3.setPrdStock(200);
			p3.setPrdDesc("전 세계 어디서나 사용 가능한 어댑터");
			p3.setPrdCategory("etc");
			p3.setPrdImg("adapter1.jpg");
			productRepo.save(p3);
			
			ProductDTO p4 = new ProductDTO();
			p4.setPrdName("휴대용 여행 저울");
			p4.setPrdPrice(5000);
			p4.setPrdStock(200);
			p4.setPrdDesc("캐리어 무게 측정 필수템");
			p4.setPrdCategory("etc");
			p4.setPrdImg("scale1.jpg");
			productRepo.save(p4);

			System.out.println(">>> [초기화] 상품 4개 생성 완료!");
		}
	}

	private void createReviews() {
		// 리뷰가 하나도 없을 때 캐리어에 대한 샘플 리뷰 1개 생성
		if (reviewRepo.count() == 0) {
			MemberDTO user = memberRepo.findById("user1").orElse(null);
			// 샘플 중 첫번째 캐리어 상품 가져오기
			ProductDTO product = productRepo.findAll().stream()
					.filter(p -> p.getPrdName().contains("캐리어"))
					.findFirst()
					.orElse(null);

			if (user != null && product != null) {
				ReviewDTO review = new ReviewDTO();
				review.setMember(user);
				review.setProduct(product);
				review.setReviewContent("튼튼하고 배송도 빨라요! 강력 추천합니다.");
				review.setReviewScore(5);
				review.setReviewDate(new Date());
				review.setReviewImg("carrier1.jpg"); 
				
				reviewRepo.save(review);
				System.out.println(">>> [초기화] 리뷰 샘플 생성 완료!");
			}
		}
	}
}