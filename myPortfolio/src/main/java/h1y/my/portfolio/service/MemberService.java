package h1y.my.portfolio.service;

import java.util.List;

import h1y.my.portfolio.dto.MemberJoinRequestDto;
import h1y.my.portfolio.entity.Member;

public interface MemberService {

	Long save(MemberJoinRequestDto memberJoinRequestDto);
	
	Member getMember(Long id);
	
	List<Member> getMembers();
	
}