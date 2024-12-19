package h1y.my.portfolio.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import h1y.my.portfolio.dto.ExperiencedRequestDto;
import h1y.my.portfolio.dto.ExperiencedResponseDto;

public interface ExperiencedService {

	Long setExperienced(ExperiencedRequestDto experiencedRequestDto, String authorization);
	
	Page<ExperiencedResponseDto> getExperienced(Pageable pageable);
	
	Page<ExperiencedResponseDto> getExperienced(Long id, Pageable pageable);
	
}
