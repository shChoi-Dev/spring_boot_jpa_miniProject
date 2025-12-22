package com.spring_boot_jpa_miniProject.project.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCT")
public class ProductDTO {
	@Id
	private int prdNo;
	private String prdName;
	private int prdPrice;
	private String prdImg;
	private int prdStock;
	private String prdDesc; // 상품 설명 필드
	private String prdCategory; // 카테고리 필드
}
