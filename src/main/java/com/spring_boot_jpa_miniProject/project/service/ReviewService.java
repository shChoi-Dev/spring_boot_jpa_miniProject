package com.spring_boot_jpa_miniProject.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring_boot_jpa_miniProject.project.dto.ReviewDTO;
import com.spring_boot_jpa_miniProject.project.repository.ReviewRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public List<ReviewDTO> getBestReviews() {
        return reviewRepository.findTop2ByOrderByRegDateDesc();
    }
}
