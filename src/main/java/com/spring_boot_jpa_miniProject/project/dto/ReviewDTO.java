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
@Table(name = "REVIEW")
public class ReviewDTO {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rev_seq_gen")
    @SequenceGenerator(name = "rev_seq_gen", sequenceName = "REV_SEQ", allocationSize = 1)
    private Long reviewNo;           // 리뷰 번호

    @ManyToOne
    @JoinColumn(name = "MEM_ID")
    private MemberDTO member;     // 작성자

    @ManyToOne
    @JoinColumn(name = "PRD_NO")
    private ProductDTO product;   // 상품

    private Long ordNo;           // 주문 번호

    private String reviewContent;    // 내용
    private int reviewScore;         // 별점
    
    @CreationTimestamp
    private Date reviewDate;         // 작성일
    
    private String reviewImg;
}
