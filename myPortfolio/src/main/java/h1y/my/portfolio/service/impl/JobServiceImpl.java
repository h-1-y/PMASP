package h1y.my.portfolio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import h1y.my.portfolio.dto.JobRequestDto;
import h1y.my.portfolio.dto.JobResponseDto;
import h1y.my.portfolio.entity.Job;
import h1y.my.portfolio.repository.JobRepository;
import h1y.my.portfolio.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobServiceImpl implements JobService {

	private final JobRepository jobRepository;
	
	@Override
	public Long setJob(JobRequestDto jobRequestDto) {
		
		int count = jobRepository.findJobNameCheck(jobRequestDto.getName());
		
		if ( count >= 1 ) throw new IllegalStateException("이미 존재하는 직군입니다.");
		
		Job job = jobRequestDto.toEntity();
		jobRepository.save(job);
		
		return job.getId();
		
	}
	
	@Override
	public JobResponseDto getJob(Long id) {
		return jobRepository.getJob(id);
	}
	
	@Override
	public Page<JobResponseDto> getJobs(Pageable pageable) {
		return jobRepository.getJobs(pageable);
	}
	
}
