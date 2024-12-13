package h1y.my.portfolio.service;

import h1y.my.portfolio.dto.ExperiencedRequestDto;

public interface ExperiencedService {

	Long setExperienced(ExperiencedRequestDto experiencedRequestDto, String authorization);
	
}
