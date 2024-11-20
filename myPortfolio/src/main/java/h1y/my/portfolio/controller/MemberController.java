package h1y.my.portfolio.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import h1y.my.portfolio.dto.MemberJoinRequestDto;
import h1y.my.portfolio.entity.Member;
import h1y.my.portfolio.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	@PostMapping("/api/v1/member")
	public Long save(@Valid MemberJoinRequestDto memberJoinRequestDto) {
		return memberService.save(memberJoinRequestDto);
	}
	
//	@PostMapping("/api/v1/member")
//	public ResponseEntity<String> save(@Valid MemberJoinRequestDto memberJoinRequestDto) {
//		memberService.save(memberJoinRequestDto);
//		return ResponseEntity.ok("User registered successfully.");
//	}
	
	@GetMapping("/api/v1/members")
	public ResponseEntity<List<Member>> getMembers() {
		return ResponseEntity.ok(memberService.getMembers());
	}
	
	@GetMapping("/api/v1/member/{id}")
	public ResponseEntity<Member> getMember(@PathVariable("id") Long id) {
		return ResponseEntity.ok(memberService.getMember(id));
	}
	
}
