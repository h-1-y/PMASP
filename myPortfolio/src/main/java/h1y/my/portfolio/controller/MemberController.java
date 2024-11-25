package h1y.my.portfolio.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import h1y.my.portfolio.dto.DataResponseDto;
import h1y.my.portfolio.dto.MemberJoinRequestDto;
import h1y.my.portfolio.entity.Member;
import h1y.my.portfolio.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController { 

	private final MemberService memberService;
	
	@PostMapping("/api/v1/members")
	public DataResponseDto<Object> save(@Valid MemberJoinRequestDto memberJoinRequestDto) {
		return DataResponseDto.of(memberService.join(memberJoinRequestDto), "회원가입 처리되었습니다.");
	}
	
	@GetMapping("/api/v1/members")
	public DataResponseDto<List<Member>> getMembers() {
		return DataResponseDto.of(memberService.getMembers());
	}
	
	@GetMapping("/api/v1/members/{id}")
	public DataResponseDto<Member> getMember(@PathVariable("id") Long id) {
		return DataResponseDto.of(memberService.getMember(id));
	}
	
}
