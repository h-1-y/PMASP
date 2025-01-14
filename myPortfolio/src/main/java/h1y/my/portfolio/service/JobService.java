package h1y.my.portfolio.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import h1y.my.portfolio.dto.JobRequestDto;
import h1y.my.portfolio.dto.JobResponseDto;

public interface JobService {

	Long setJob(JobRequestDto jobRequestDto);
	
	JobResponseDto getJob(Long id);
	
	Page<JobResponseDto> getJobs(Pageable pageable);
	
}
