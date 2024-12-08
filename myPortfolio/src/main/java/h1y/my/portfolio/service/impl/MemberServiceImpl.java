package h1y.my.portfolio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import h1y.my.portfolio.dto.MemberJoinRequestDto;
import h1y.my.portfolio.dto.MemberResponseDto;
import h1y.my.portfolio.dto.MemberSearchDto;
import h1y.my.portfolio.entity.Job;
import h1y.my.portfolio.entity.Member;
import h1y.my.portfolio.repository.JobJpaRepository;
import h1y.my.portfolio.repository.MemberJpaRepositoy;
import h1y.my.portfolio.repository.MemberRepository;
import h1y.my.portfolio.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

	private final MemberJpaRepositoy memberJpaRepositoy;
	private final MemberRepository memberRepository;
	private final JobJpaRepository jobJpaRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public Long join(MemberJoinRequestDto memberJoinRequestDto) {
		
		// 회원 아이디 중복 체크
		int count = memberJpaRepositoy.findJoinLoginIdCheck(memberJoinRequestDto.getLoginId());
		
		if ( count >= 1 ) throw new IllegalStateException("이미 존재하는 회원입니다.");
		
		Job job = jobJpaRepository.findById(memberJoinRequestDto.getJobId())
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 직군입니다."));
		
		memberJoinRequestDto.setPassword(passwordEncoder.encode(memberJoinRequestDto.getPassword()));
		
		Member member = memberJoinRequestDto.toEntity(job);
		memberJpaRepositoy.save(member);
		
		return member.getId();
		
	}
	
	@Override
	public MemberResponseDto getMember(Long id) {
		MemberResponseDto findMember = memberJpaRepositoy.getMember(id);
		return findMember;
	}
	
	@Override
	public List<MemberResponseDto> getMembers(MemberSearchDto memberSearchDto) {
		List<MemberResponseDto> members = memberRepository.getResponseMembers(memberSearchDto);
		return members;
	}
	
}
