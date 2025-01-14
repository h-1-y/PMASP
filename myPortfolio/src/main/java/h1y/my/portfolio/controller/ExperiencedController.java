package h1y.my.portfolio.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import h1y.my.portfolio.common.dto.DataResponseDto;
import h1y.my.portfolio.dto.ExperiencedRequestDto;
import h1y.my.portfolio.dto.ExperiencedResponseDto;
import h1y.my.portfolio.service.ExperiencedService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ExperiencedController {

	private final ExperiencedService experiencedService;
	
	@PostMapping("/api/v1/experienced")
	public DataResponseDto<Long> save(
			  @Valid @RequestBody ExperiencedRequestDto experiencedRequestDto
			, @RequestHeader("Authorization") String authorization
	) {
		return DataResponseDto.of(experiencedService.setExperienced(experiencedRequestDto, authorization), "경력사항이 등록되었습니다.");
	}
	
	@GetMapping("/api/v1/experienced")
	public DataResponseDto<Page<ExperiencedResponseDto>> getExperienceds(Pageable pageable) {
		return DataResponseDto.of(experiencedService.getExperienced(pageable));
	}
	
	@GetMapping("/api/v1/experienced/{id}")
	public DataResponseDto<Page<ExperiencedResponseDto>> getExperienced(@PathVariable("id") Long id, Pageable pageable) {
		return DataResponseDto.of(experiencedService.getExperienced(id, pageable));
	}
	
}
