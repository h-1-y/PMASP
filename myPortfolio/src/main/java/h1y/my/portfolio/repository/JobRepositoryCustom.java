package h1y.my.portfolio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import h1y.my.portfolio.dto.JobResponseDto;

public interface JobRepositoryCustom {

	Page<JobResponseDto> getJobs(Pageable pageable);
	
}
