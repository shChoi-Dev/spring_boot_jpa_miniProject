package com.spring_boot_jpa_miniProject.project;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.spring_boot_jpa_miniProject.project.service.OrderService;

@SpringBootTest
@Transactional
class IntegrationTest {

	@Autowired
	private OrderService orderService;
	
	@Test
	void totalSalesTest() {
		// 실제 DB에 있는 주문 데이터를 바탕으로 매출액 계산
		long totalSales = orderService.getTotalSales();
		
		System.out.println("현재 총 매출액: " + totalSales);
		
		// 매출액은 0보다 크거나 같아야 함. (검증)
		assertThat(totalSales).isGreaterThanOrEqualTo(0);
	}

}
