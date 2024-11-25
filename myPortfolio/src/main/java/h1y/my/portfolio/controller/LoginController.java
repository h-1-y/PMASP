package h1y.my.portfolio.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import h1y.my.portfolio.dto.DataResponseDto;
import h1y.my.portfolio.dto.LoginRequestDto;
import h1y.my.portfolio.dto.MemberJoinRequestDto;
import h1y.my.portfolio.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class LoginController {

	private final LoginService loginService;
	
	@PostMapping("login")
	public DataResponseDto<String> login(@Valid LoginRequestDto loginRequestDto) {
		return DataResponseDto.of(loginService.login(loginRequestDto), "로그인 되었습니다.");
	}
	
}
