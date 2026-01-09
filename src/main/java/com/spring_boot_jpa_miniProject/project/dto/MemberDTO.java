package com.spring_boot_jpa_miniProject.project.dto;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "MEMBER")
public class MemberDTO {
	@Id
	private String memId; // 아이디 필드
	private String memPwd; // 비밀번호 필드
	private String memName; // 이름 필드
	private String memEmail; // 이메일 필드
	private Date memJoindate; // 가입일 필드
	private String memHp; // 연락처 필드
	private String memZipcode; // 우편번호 필드
	private String memAddress1; // 주소 필드
	private String memAddress2; // 상세주소 필드
	private String memRole; // 역할 필드

	// 계정 활성화 여부 (Y: 사용중, N: 탈퇴)
	private String memActive;

	// insert 되기 전에 실행되어 기본값을 설정하는 메서드
	@PrePersist
	public void prePersist() {
		if (this.memActive == null) {
			this.memActive = "Y"; // 기본값은 'Y' (활동중)
		}
	}
}
