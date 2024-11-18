package h1y.my.portfolio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import h1y.my.portfolio.dto.MemberDto;
import h1y.my.portfolio.entity.Member;
import h1y.my.portfolio.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	
	@Override
	public Long save(MemberDto memberDto) {
		
		Member member = memberDto.toEntity();
		memberRepository.save(member);
		
		return member.getId();
		
	}
	
	@Override
	public Member getMember(Long id) {
		Member findMember = memberRepository.findById(id);
		return findMember;
	}
	
	@Override
	public List<Member> getMembers() {
		List<Member> members = memberRepository.findAll();
		return members;
	}
	
}
