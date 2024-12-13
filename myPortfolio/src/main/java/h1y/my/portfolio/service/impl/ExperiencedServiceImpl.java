package h1y.my.portfolio.service.impl;

import org.springframework.stereotype.Service;

import h1y.my.portfolio.config.jwt.JwtUtils;
import h1y.my.portfolio.dto.ExperiencedRequestDto;
import h1y.my.portfolio.repository.ExperiencedRepository;
import h1y.my.portfolio.service.ExperiencedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExperiencedServiceImpl implements ExperiencedService {

	private final ExperiencedRepository experiencedRepository;
	private final JwtUtils jwtUtils;
	
	@Override
	public Long setExperienced(ExperiencedRequestDto experiencedRequestDto, String authorization) {
		
		Long memberId = jwtUtils.getMemberId(authorization.replace("Bearer ", ""));
		
		
		return null;
		
	}
	
}
