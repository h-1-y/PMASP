package h1y.my.portfolio.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import h1y.my.portfolio.dto.MemberJoinRequestDto;
import h1y.my.portfolio.dto.MemberResponseDto;
import h1y.my.portfolio.dto.MemberSearchDto;

public interface MemberService {

	Long join(MemberJoinRequestDto memberJoinRequestDto);
	
	MemberResponseDto getMember(Long id);
	
	Page<MemberResponseDto> getMembers(MemberSearchDto memberSearchDto, Pageable pageable);
	
}
