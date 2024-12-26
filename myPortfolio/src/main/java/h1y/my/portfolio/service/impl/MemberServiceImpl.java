package h1y.my.portfolio.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import h1y.my.portfolio.dto.MemberJoinRequestDto;
import h1y.my.portfolio.dto.MemberResponseDto;
import h1y.my.portfolio.dto.MemberSearchDto;
import h1y.my.portfolio.entity.Job;
import h1y.my.portfolio.entity.Member;
import h1y.my.portfolio.repository.JobRepository;
import h1y.my.portfolio.repository.MemberRepositoy;
import h1y.my.portfolio.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

	private final MemberRepositoy memberRepositoy;
	private final JobRepository jobJpaRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public Long join(MemberJoinRequestDto memberJoinRequestDto) {
		
		// 회원 아이디 중복 체크
		int count = memberRepositoy.findJoinLoginIdCheck(memberJoinRequestDto.getLoginId());
		
		if ( count >= 1 ) throw new IllegalStateException("이미 존재하는 회원입니다.");
		
		Job job = jobJpaRepository.findById(memberJoinRequestDto.getJobId())
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 직군입니다."));
		
		memberJoinRequestDto.setPassword(passwordEncoder.encode(memberJoinRequestDto.getPassword()));
		
		Member member = memberJoinRequestDto.toEntity(job);
		memberRepositoy.save(member);
		
		return member.getId();
		
	}
	
	@Override
	public MemberResponseDto getMember(Long id) {
		MemberResponseDto findMember = memberRepositoy.getMember(id);
		return findMember;
	}
	
	@Override
	public Page<MemberResponseDto> getMembers(MemberSearchDto memberSearchDto, Pageable pageable) {
		Page<MemberResponseDto> members = memberRepositoy.getMembers(memberSearchDto, pageable);
		return members;
	}
	
}
