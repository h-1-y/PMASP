package h1y.my.portfolio.dto;

import h1y.my.portfolio.entity.Experienced;
import h1y.my.portfolio.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ExperiencedRequestDto {

	@NotBlank(message = "이름을 입력해주세요.")
	@Size(min = 2, max = 30, message = "이름은 2자 ~ 30자 사이로 입력해주세요.")
	private String companyName;
	
	@NotBlank(message = "입사일을 입력해주세요.")
	@Size(max = 6, message = "입사일은 202004 형식의 6자로 입력해주세요.")
	private String startDate;
	
	@Size(max = 6, message = "퇴사일은 202004 형식의 6자로 입력해주세요.")
	private String endDate;
	
	@NotBlank(message = "부서명을 입력해주세요.")
	@Size(min = 2, max = 20, message = "부서명은 2자 ~ 20자 사이로 입력해주세요.")
	private String department;
	
	@NotBlank(message = "직급을 입력해주세요.")
	@Size(min = 2, max = 20, message = "직급은 2자 ~ 20자 사이로 입력해주세요.")
	private String rank;
	
	public Experienced toEntity(Member member) {
		return Experienced.builder()
				.member(member)
				.companyName(this.companyName)
				.startDate(this.startDate)
				.endDate(this.endDate)
				.department(this.department)
				.rank(this.rank)
				.build();
	}
	
}
