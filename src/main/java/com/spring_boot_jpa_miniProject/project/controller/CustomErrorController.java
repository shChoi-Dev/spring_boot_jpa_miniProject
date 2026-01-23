package com.spring_boot_jpa_miniProject.project.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

	// 에러 발생 시 이 메서드가 호출
	@GetMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
		// 발생한 에러 코드 가져오기 (예: 404, 500)
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (status != null) {
			int statusCode = Integer.valueOf(status.toString());
			model.addAttribute("errorCode", statusCode);

			// 에러 코드별 메시지 설정
			if (statusCode == 404) {
				model.addAttribute("errorMessage", "요청하신 페이지를 찾을 수 없습니다.");
				model.addAttribute("errorDetail", "주소가 잘못 입력되었거나, 변경 혹은 삭제되어 요청하신 페이지를 찾을 수 없습니다.");
			} else if (statusCode == 500) {
				model.addAttribute("errorMessage", "서버 내부 오류가 발생했습니다.");
				model.addAttribute("errorDetail", "시스템 문제로 인해 요청을 처리할 수 없습니다. 잠시 후 다시 시도해 주세요.");
			} else if (statusCode == 403) {
				model.addAttribute("errorMessage", "접근 권한이 없습니다.");
				model.addAttribute("errorDetail", "이 페이지는 관리자만 접근할 수 있습니다.<br>관리자 계정으로 로그인해 주세요.");
			} else {
				model.addAttribute("errorMessage", "알 수 없는 오류가 발생했습니다.");
				model.addAttribute("errorDetail", "죄송합니다. 요청을 처리하는 도중 문제가 발생했습니다.");
			}
		}

		// common 폴더 아래의 error.jsp로 이동
		return "common/error";
	}
}
