package h1y.my.portfolio.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import h1y.my.portfolio.common.dto.DataResponseDto;
import h1y.my.portfolio.dto.MemberJoinRequestDto;
import h1y.my.portfolio.dto.MemberResponseDto;
import h1y.my.portfolio.dto.MemberSearchDto;
import h1y.my.portfolio.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController { 

	private final MemberService memberService;
	
	@PostMapping("/api/v1/member")
	public DataResponseDto<Long> save(@Valid @RequestBody MemberJoinRequestDto memberJoinRequestDto) {
		return DataResponseDto.of(memberService.join(memberJoinRequestDto), "회원가입 처리되었습니다.");
	}
	
	@GetMapping("/api/v1/member")
	public DataResponseDto<Page<MemberResponseDto>> getMembers(MemberSearchDto memberSearchDto, Pageable pageable) {
		return DataResponseDto.of(memberService.getMembers(memberSearchDto, pageable));
	}
	
	@GetMapping("/api/v1/member/{id}")
	public DataResponseDto<MemberResponseDto> getMember(@PathVariable("id") Long id) {
		return DataResponseDto.of(memberService.getMember(id));
	}
	
}
