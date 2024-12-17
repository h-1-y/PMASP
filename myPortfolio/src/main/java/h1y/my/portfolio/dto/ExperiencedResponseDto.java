package h1y.my.portfolio.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class ExperiencedResponseDto {

	private Long id;
	private Long memberId;
	private String memberName;
	private String companyName;
	private String startDate;
	private String endDate;
	private String department;
	private String rank;
	
	@QueryProjection
	public ExperiencedResponseDto(Long id, Long memberId, String memberName, String companyName, String startDate,
			String endDate, String department, String rank) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.memberName = memberName;
		this.companyName = companyName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.department = department;
		this.rank = rank;
	}
	
}
