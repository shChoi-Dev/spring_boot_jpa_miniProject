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
@Table(name = "CART")
public class CartDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq_gen")
	@SequenceGenerator(name = "cart_seq_gen", sequenceName = "CART_SEQ", allocationSize = 1)
	private Long cartNo;      // 장바구니 번호 (PK)
	
	@ManyToOne
	@JoinColumn(name = "MEM_ID")
	private MemberDTO member; // 회원 정보 (FK)
	
	@ManyToOne
	@JoinColumn(name = "PRD_NO")
	private ProductDTO product; // 상품 정보 (FK)

    private int cartQty;      // 수량

    @CreationTimestamp // INSERT 시 현재 시간 자동 저장
    private Date regDate;     // 담은 날짜

}
