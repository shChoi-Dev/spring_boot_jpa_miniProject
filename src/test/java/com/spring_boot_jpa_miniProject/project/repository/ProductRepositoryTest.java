package com.spring_boot_jpa_miniProject.project.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.spring_boot_jpa_miniProject.project.dto.ProductDTO;

@DataJpaTest // JPA 관련 설정만 로드
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 DB 설정 사용
class ProductRepositoryTest {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Test
	@DisplayName("상품명 검색 쿼리 테스트") // 테스트 이름 지정
	void searchTest() {
		// 테스트 데이터 저장 => 준비
		ProductDTO p1 = new ProductDTO();
		p1.setPrdName("테스트용 캐리어");
		p1.setPrdPrice(10000);
		productRepository.save(p1);
		
		ProductDTO p2 = new ProductDTO();
		p2.setPrdName("테스트용 가방");
		p2.setPrdPrice(20000);
		productRepository.save(p2);
		
		// 우리가 만든 검색 메서드 호출 => 실행
		List<ProductDTO> result = productRepository.findByPrdNameContaining("테스트용 캐리어");
		
		// 결과가 맞는지 확인 => 검증
		assertThat(result.size()).isEqualTo(1);
		assertThat(result.get(0).getPrdName()).isEqualTo("테스트용 캐리어");
	}
	
}
