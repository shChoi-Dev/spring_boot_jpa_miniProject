package com.spring_boot_jpa_miniProject.project.dto;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ORDERS")
public class OrderDTO {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ord_seq_gen")
    @SequenceGenerator(name = "ord_seq_gen", sequenceName = "ORD_SEQ", allocationSize = 1)
    private Long ordNo;           // 주문 번호 (PK)

    @ManyToOne
    @JoinColumn(name = "MEM_ID")
    private MemberDTO member;     // 주문자 정보 (FK)

    @CreationTimestamp
    private Date ordDate;         // 주문 날짜

    private String ordStatus;     // 주문 상태 (ORDERED, CANCEL 등)
    
    private String ordName;       // 받는 사람 이름
    private String ordZipcode;    // 우편번호
    private String ordAddr1;      // 기본 주소
    private String ordAddr2;      // 상세 주소
    private String ordPhone;      // 연락처
    
    private Long ordTotalPrice;   // 총 주문 금액
}
