package h1y.my.portfolio.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import h1y.my.portfolio.config.jwt.JwtUtils;
import h1y.my.portfolio.dto.ExperiencedRequestDto;
import h1y.my.portfolio.dto.ExperiencedResponseDto;
import h1y.my.portfolio.entity.Experienced;
import h1y.my.portfolio.entity.Member;
import h1y.my.portfolio.repository.ExperiencedRepository;
import h1y.my.portfolio.repository.MemberRepositoy;
import h1y.my.portfolio.service.ExperiencedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExperiencedServiceImpl implements ExperiencedService {

	private final ExperiencedRepository experiencedRepository;
	private final MemberRepositoy memberRepositoy;
	private final JwtUtils jwtUtils;
	
	@Override
	public Long setExperienced(ExperiencedRequestDto experiencedRequestDto, String authorization) {
		
		Long memberId = jwtUtils.getMemberId(authorization.replace("Bearer ", ""));
		Member findMember = memberRepositoy.findById(memberId)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
		
		Experienced exp = experiencedRequestDto.toEntity(findMember);
		experiencedRepository.save(exp);
		
		return exp.getId();
		
	}
	
	@Override
	public Page<ExperiencedResponseDto> getExperienced(Pageable pageable) {
		Page<ExperiencedResponseDto> exps = experiencedRepository.getExperienced(pageable);
		return exps;
	}
	
	@Override
	public Page<ExperiencedResponseDto> getExperienced(Long id, Pageable pageable) {
		Page<ExperiencedResponseDto> exps = experiencedRepository.getExperienced(id, pageable);
		return exps;
	}
	
}
