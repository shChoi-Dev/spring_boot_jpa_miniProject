package com.spring_boot_jpa_miniProject.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring_boot_jpa_miniProject.project.dto.MemberDTO;
import com.spring_boot_jpa_miniProject.project.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memRepo;

	@Autowired
	private PasswordEncoder passEncoder;

	// 회원 정보 저장 (회원가입)
	public void saveMember(MemberDTO member) {
		// 비밀번호를 암호화
		String encodedPassword = passEncoder.encode(member.getMemPwd());
		member.setMemPwd(encodedPassword);

		// 권한이 없으면 "USER"로 강제 설정
		if (member.getMemRole() == null) {
			member.setMemRole("USER");
		}

		// 활성화 상태 기본값 "Y" 설정
		if (member.getMemActive() == null) {
			member.setMemActive("Y");
		}

		// 암호화된 비밀번호화 함께 회원 정보 저장
		memRepo.save(member);
	}

	// 로그인 확인 로직
	public MemberDTO loginCheck(String memId, String memPwd) {
		// 전달받은 아이디로 회원 정보 조회
		MemberDTO member = memRepo.findById(memId).orElse(null);

		// 회원이 존재하고 + 비밀번호가 맞고 + "활동중(Y)"인 경우에만 성공
		if (member != null && passEncoder.matches(memPwd, member.getMemPwd())) {
			if ("Y".equals(member.getMemActive())) {
				return member; // 인증 성공 시 MemberDTO 객체 반환
			}
		}
		return null; // 비번이 틀리거나, 탈퇴한 회원이면 null 반환
	}

	// 회원 정보 조회 (마이페이지, 주문서 작성 시 사용)
	public MemberDTO getMemberInfo(String memId) {
		// ID로 회원 정보를 찾아서 반환 (없으면 null 반환)
		return memRepo.findById(memId).orElse(null);
	}

	// 회원 정보 수정
	public void updateMember(MemberDTO updateMem) {
		// DB에서 기존 정보 가져오기
		MemberDTO member = memRepo.findById(updateMem.getMemId()).orElse(null);

		if (member != null) {
			// 수정 가능한 정보들 업데이트
			member.setMemEmail(updateMem.getMemEmail());
			member.setMemHp(updateMem.getMemHp());
			member.setMemZipcode(updateMem.getMemZipcode());
			member.setMemAddress1(updateMem.getMemAddress1());
			member.setMemAddress2(updateMem.getMemAddress2());

			// 비밀번호 변경 로직 (비밀번호 입력란이 비어있지 않다면 변경)
			if (updateMem.getMemPwd() != null && !updateMem.getMemPwd().isEmpty()) {
				String encodedPassword = passEncoder.encode(updateMem.getMemPwd());
				member.setMemPwd(encodedPassword);
			}

			memRepo.save(member); // 변경사항 저장 (JPA는 ID가 같으면 update 수행)
		}
	}

	// 비밀번호 확인 (정보 수정/탈퇴 전 본인 확인용)
	public boolean checkPassword(String memId, String inputPwd) {
		MemberDTO member = memRepo.findById(memId).orElse(null);
		if (member != null) {
			return passEncoder.matches(inputPwd, member.getMemPwd());
		}
		return false;
	}

	// 회원 탈퇴 (Soft Delete)
	public void withdrawMember(String memId) {
		MemberDTO member = memRepo.findById(memId).orElse(null);
		if (member != null) {
			member.setMemActive("N"); // 상태를 "N"으로 변경
			memRepo.save(member);
		}
	}

	// 총 회원 수 조회
	public long getMemberCount() {
		return memRepo.count();
	}
}
