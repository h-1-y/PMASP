package h1y.my.portfolio.service;

import java.util.List;

import h1y.my.portfolio.dto.JobRequestDto;
import h1y.my.portfolio.entity.Job;

public interface JobService {

	Long setJob(JobRequestDto jobRequestDto);
	
	Job getJob(Long id);
	
	List<Job> getJobs();
	
}
