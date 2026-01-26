package com.spring_boot_jpa_miniProject.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring_boot_jpa_miniProject.project.dto.OrderDTO;

public interface OrderRepository extends JpaRepository<OrderDTO, Long> {
	// 회원의 주문 내역 조회 (최신순 정렬)
	List<OrderDTO> findAllByMemberMemIdOrderByOrdDateDesc(String memId);
	
	// 관리자: 모든 주문 내역 조회 (최신순 정렬)
    List<OrderDTO> findAllByOrderByOrdDateDesc();
    
    // 특정 상태인 주문의 개수 세기 (ex: ORDERED 상태가 몇 개인지?)
    long countByOrdStatus(String ordStatus);
}
