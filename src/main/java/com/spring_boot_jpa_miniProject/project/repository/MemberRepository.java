package com.spring_boot_jpa_miniProject.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring_boot_jpa_miniProject.project.dto.MemberDTO;

public interface MemberRepository extends JpaRepository<MemberDTO, String> {
	// JpaRepository<DTO클래스, PK의 타입>
}
