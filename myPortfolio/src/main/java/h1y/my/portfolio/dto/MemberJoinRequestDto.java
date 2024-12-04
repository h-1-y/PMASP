package h1y.my.portfolio.dto;

import org.hibernate.annotations.Comment;

import h1y.my.portfolio.entity.Address;
import h1y.my.portfolio.entity.Job;
import h1y.my.portfolio.entity.Member;
import jakarta.persistence.Column;
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
	
//	@NotBlank(message = "직군을 선택해주세요.")
	private Long jobId;
	
	@NotBlank(message = "주소를 입력해주세요.")
	private String address;
	
	private String addressDetail;
	
	@NotBlank(message = "우편번호를 입력해주세요.")
	private String zipcode;
	
	public Member toEntity(Job job) {
		return Member.builder()
				.loginId(this.loginId)
				.password(this.password)
				.name(this.name)
				.job(job)
				.address(new Address(this.address, this.addressDetail, this.zipcode))
				.build();
	}
	
}
