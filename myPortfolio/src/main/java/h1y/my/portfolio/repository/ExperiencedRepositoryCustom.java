package h1y.my.portfolio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import h1y.my.portfolio.dto.ExperiencedResponseDto;

public interface ExperiencedRepositoryCustom {

	Page<ExperiencedResponseDto> getExperienced(Long id, Pageable pageable);
	
}
