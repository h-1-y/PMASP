package h1y.my.portfolio.dto;

import h1y.my.portfolio.entity.Member;
import lombok.Data;

@Data
public class MemberDto {

	private Long id;
	private String loginId;
	private String password;
	private String name;
	
	public Member toEntity() {
		return Member.builder()
				.loginId(this.loginId)
				.password(this.password)
				.name(this.name)
				.build();
	}
	
}
