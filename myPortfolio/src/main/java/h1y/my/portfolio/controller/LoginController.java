package h1y.my.portfolio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import h1y.my.portfolio.dto.DataResponseDto;
import h1y.my.portfolio.dto.LoginRequestDto;
import h1y.my.portfolio.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class LoginController {

	private final LoginService loginService;
	
	@PostMapping("login")
	public ResponseEntity<DataResponseDto<String>> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
		
		String token = loginService.login(loginRequestDto);
		
		return ResponseEntity.ok()
				.header("Authorization", "Bearer " + token)
				.body(DataResponseDto.of(token, "로그인 되었습니다."));
		
	}
	
}
