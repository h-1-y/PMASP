package h1y.my.portfolio.dto;

import h1y.my.portfolio.entity.Job;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JobRequestDto {

	@NotBlank(message = "직군을 입력해주세요.")
	private String name;
	
	public Job toEntity() {
		return Job.builder()
				.name(this.name)
				.build();
	}
	
}
