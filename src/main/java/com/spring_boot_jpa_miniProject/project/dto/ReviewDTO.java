package com.spring_boot_jpa_miniProject.project.dto;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "REVIEW")
public class ReviewDTO {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq_generator")
    @SequenceGenerator(name="review_seq_generator", sequenceName="REVIEW_SEQ", allocationSize=1)
	private int reviewNo;
    private String reviewContent;
    private int reviewRating;
    private String memId;
    private int prdNo;
    private Date regDate;
}
