package com.spring_boot_jpa_miniProject.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.spring_boot_jpa_miniProject.project.dto.MemberDTO;
import com.spring_boot_jpa_miniProject.project.repository.MemberRepository;

@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired
	private MemberRepository memberRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		// 관리자 계정 생성 (이미 존재하는지 확인 후 생성)
		if (memberRepo.findById("admin").isEmpty()) {
			MemberDTO admin = new MemberDTO();
			admin.setMemId("admin");
			admin.setMemPwd(passwordEncoder.encode("1234")); // 비밀번호 암호화
			admin.setMemName("관리자");
			admin.setMemEmail("admin@trvr.com");
			admin.setMemHp("010-0000-0000");
			admin.setMemRole("ADMIN");

			memberRepo.save(admin);
			System.out.println(">>> [초기화] 관리자 계정(admin) 생성 완료!");
		}

		// 일반 회원 계정 생성 (존재하지 않을 경우)
		if (memberRepo.findById("user1").isEmpty()) {
			MemberDTO user = new MemberDTO();
			user.setMemId("user1");
			user.setMemPwd(passwordEncoder.encode("1234")); // 비밀번호 암호화
			user.setMemName("홍길동");
			user.setMemEmail("user1@trvr.com");
			user.setMemHp("010-1234-5678");
			user.setMemRole("USER");
			
			memberRepo.save(user);
			System.out.println(">>> [초기화] 일반 회원(user1) 생성 완료!");
		}
	}
}