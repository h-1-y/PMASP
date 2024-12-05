package h1y.my.portfolio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import h1y.my.portfolio.dto.JobRequestDto;
import h1y.my.portfolio.entity.Job;
import h1y.my.portfolio.repository.JobJpaRepository;
import h1y.my.portfolio.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobServiceImpl implements JobService {

	private final JobJpaRepository jobJpaRepository;
	
	@Override
	public Long setJob(JobRequestDto jobRequestDto) {
		
		int count = jobJpaRepository.findJobNameCheck(jobRequestDto.getName());
		
		if ( count >= 1 ) throw new IllegalStateException("이미 존재하는 직군입니다.");
		
		Job job = jobRequestDto.toEntity();
		jobJpaRepository.save(job);
		
		return job.getId();
		
	}
	
	@Override
	public Job getJob(Long id) {
		return jobJpaRepository.findById(id).get();
	}
	
	@Override
	public List<Job> getJobs() {
		return jobJpaRepository.findAll();
	}
	
}
