package com.spring_boot_jpa_miniProject.project.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spring_boot_jpa_miniProject.project.dto.MemberDTO;
import com.spring_boot_jpa_miniProject.project.repository.MemberRepository;

@ExtendWith(MockitoExtension.class) // Mockito 사용 설정
class MemberServiceTest {
	
	@Mock // 가짜 리포지토리 생성
	private MemberRepository memberRepo;
	
	// 가짜 암호화 객체 생성
    @Mock
    private PasswordEncoder passwordEncoder;
	
	@InjectMocks // 가짜 리포지토리를 서비스에 주입
	private MemberService memberService;

	@Test
	@DisplayName("회원가입 서비스 테스트")
	void joinTest() {
		// 가상 시나리오 설정
		MemberDTO member = new MemberDTO();
		member.setMemId("testUser");
		member.setMemName("홍길동");
		
		// "memberRepo.save()가 호출되면, 위의 member 객체를 리턴해라"는 가짜 행동 정의
		given(memberRepo.save(any(MemberDTO.class))).willReturn(member);
		
		// 서비스 실행
		memberService.saveMember(member);
		
		// 검증
		verify(memberRepo).save(any(MemberDTO.class));
	}

}
