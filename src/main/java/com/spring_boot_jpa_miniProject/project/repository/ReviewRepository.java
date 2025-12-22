package com.spring_boot_jpa_miniProject.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring_boot_jpa_miniProject.project.dto.ReviewDTO;

public interface ReviewRepository extends JpaRepository<ReviewDTO, Integer> {
	List<ReviewDTO> findTop2ByOrderByRegDateDesc();
}
