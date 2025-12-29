package com.spring_boot_jpa_miniProject.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring_boot_jpa_miniProject.project.dto.OrderDTO;
import com.spring_boot_jpa_miniProject.project.dto.OrderDetailDTO;

public interface OrderDetailRepository extends JpaRepository<OrderDetailDTO, Long> {
	// 특정 주문번호에 해당하는 상세 상품들 조회
	List<OrderDetailDTO> findAllByOrderOrdNo(Long ordNo);
	
	// Order 객체로 조회할 경우
	List<OrderDetailDTO> findALlByOrder(OrderDTO order);
	
}
