package com.spring_boot_jpa_miniProject.project.dto;

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
@Table(name = "ORDER_DETAIL")
public class OrderDetailDTO {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detail_seq_gen")
    @SequenceGenerator(name = "detail_seq_gen", sequenceName = "DETAIL_SEQ", allocationSize = 1)
    private Long detailNo;        // 상세 번호 (PK)

    @ManyToOne
    @JoinColumn(name = "ORD_NO")
    private OrderDTO order;       // 어떤 주문인지 (FK)

    @ManyToOne
    @JoinColumn(name = "PRD_NO")
    private ProductDTO product;   // 어떤 상품인지 (FK)

    private int ordQty;           // 주문 수량
    private int ordPrice;         // 구매 당시 가격 (단가)
}
