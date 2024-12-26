package h1y.my.portfolio.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class JobResponseDto {

	private String name;

	@QueryProjection
	public JobResponseDto(String name) {
		super();
		this.name = name;
	}

}
