package com.spring_boot_jpa_miniProject.project.dto;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "MEMBER")
public class MemberDTO {
	@Id
	private String memId;		// 아이디 필드
	private String memPwd;		// 비밀번호 필드
	private String memName;		// 이름 필드
	private String memEmail;	// 이메일 필드
	private Date memJoindate;	// 가입일 필드
	private String memHp;		// 연락처 필드
	private String memZipcode;	// 우편번호 필드
	private String memAddress1;	// 주소 필드
	private String memAddress2; // 가입일 필드
	private String memRole; // 역할 필드
}
