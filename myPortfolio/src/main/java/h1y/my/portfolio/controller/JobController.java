package h1y.my.portfolio.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import h1y.my.portfolio.common.dto.DataResponseDto;
import h1y.my.portfolio.dto.JobRequestDto;
import h1y.my.portfolio.entity.Job;
import h1y.my.portfolio.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class JobController {

	private final JobService jobService;
	
	@PostMapping("/api/v1/job")
	public DataResponseDto<Long> save(@Valid @RequestBody JobRequestDto jobRequestDto) {
		return DataResponseDto.of(jobService.setJob(jobRequestDto));
	}
	
	@GetMapping("/api/v1/job")
	public DataResponseDto<List<Job>> getJobs() {
		return DataResponseDto.of(jobService.getJobs());
	}
	
	@GetMapping("/api/v1/job/{id}")
	public DataResponseDto<Job> getJob(@PathVariable("id") Long id) {
		return DataResponseDto.of(jobService.getJob(id));
	}
	
}
