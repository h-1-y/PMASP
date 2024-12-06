package h1y.my.portfolio.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class MemberResponseDto {

	private Long id;
	private String loginId;
	private String name;
	private String jobName;
	private String address;
	private String addressDetail;
	private String zipcode;
	
	@QueryProjection
	public MemberResponseDto(Long id, String loginId, String name, String jobName, String address,
			String addressDetail, String zipcode) {
		super();
		this.id = id;
		this.loginId = loginId;
		this.name = name;
		this.jobName = jobName;
		this.address = address;
		this.addressDetail = addressDetail;
		this.zipcode = zipcode;
	}
	
}
