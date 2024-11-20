package h1y.my.portfolio.dto;

import h1y.my.portfolio.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberJoinRequestDto {

	private Long id;
	
	@NotBlank(message = "아이디를 입력해주세요.")
	@Size(min = 3, max = 16, message = "아이디는 3자 ~ 16자 사이로 입력해주세요.")
	private String loginId;
	
	@NotBlank(message = "비밀번호를 입력해주세요.")
	@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
    		 message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
	private String password;
	
	@NotBlank(message = "이름을 입력해주세요.")
	@Size(min = 2, max = 30, message = "이름은 2자 ~ 30자 사이로 입력해주세요.")
	private String name;
	
	public Member toEntity() {
		return Member.builder()
				.loginId(this.loginId)
				.password(this.password)
				.name(this.name)
				.build();
	}
	
}
