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
		
		// 암호화된 비밀번호화 함께 회원 정보 저장
		memRepo.save(member);	
	}
	
	// 로그인 확인 로직
	public MemberDTO loginCheck(String memId, String memPwd) {
		// 전달받은 아이디로 회원 정보 조회
		MemberDTO member = memRepo.findById(memId).orElse(null);
		
		if (member != null) { // 조회된 회원 정보가 있다면
			// DB에 저장된 암호화된 비밀번호와 입력된 비밀번호를 비교
			if (passEncoder.matches(memPwd, member.getMemPwd())) {
				return member; // 인증 성공 시 MemberDTO 객체 반환
			}
		}
		return null; // 인증 실패 시 null 반환
	}
	
	// 회원 정보 조회 (마이페이지, 주문서 작성 시 사용)
    public MemberDTO getMemberInfo(String memId) {
        // ID로 회원 정보를 찾아서 반환 (없으면 null 반환)
        return memRepo.findById(memId).orElse(null);
    }
}
