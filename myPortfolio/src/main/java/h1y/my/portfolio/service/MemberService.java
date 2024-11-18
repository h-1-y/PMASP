package h1y.my.portfolio.service;

import java.util.List;

import h1y.my.portfolio.dto.MemberDto;
import h1y.my.portfolio.entity.Member;

public interface MemberService {

	Long save(MemberDto memberDto);
	
	Member getMember(Long id);
	
	List<Member> getMembers();
	
}
