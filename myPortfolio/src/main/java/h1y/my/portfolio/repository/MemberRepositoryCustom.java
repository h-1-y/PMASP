package h1y.my.portfolio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import h1y.my.portfolio.dto.MemberResponseDto;
import h1y.my.portfolio.dto.MemberSearchDto;

public interface MemberRepositoryCustom {

	Page<MemberResponseDto> getMembers(MemberSearchDto memberSearchDto, Pageable pageable);
	
}
