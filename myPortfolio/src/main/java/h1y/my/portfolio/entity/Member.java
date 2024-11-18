package h1y.my.portfolio.entity;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Member extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "M_ID")
	@Comment(value = "일련번호")
	private Long id;
	
	@Column(name = "M_LOGIN_ID")
	@Comment(value = "아이디")
	private String loginId;
	
	@Column(name = "M_PASSWORD")
	@Comment(value = "비밀번호")
	private String password;
	
	@Column(name = "M_NAME")
	@Comment(value = "성명")
	private String name;
	
//	@Column(name = "M_BIRTHDATE")
//	@Comment(value = "생년월일")
//	private String birthdate;
//	
//	@Column(name = "M_EMAIL")
//	@Comment(value = "이메일")
//	private String email;
//	
//	@Column(name = "M_PHONE")
//	@Comment(value = "전화번호")
//	private String phone;
//	
//	@Column(name = "M_ADRS")
//	@Comment(value = "주소")
//	private String address;
//	
//	@Column(name = "M_ADRS_DTL")
//	@Comment(value = "상세 주소")
//	private String addressDetail;
//	
//	@Column(name = "M_ZIPCODE")
//	@Comment(value = "우편번호")
//	private String zipcode;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "job_id")
//	@Comment(value = "직군 일련번호")
//	private Job job;
//	
//	@Column(name = "EXPERIENCED_YN")
//	@Comment(value = "경력 여부")
//	private String experiencedYn;
//	
//	@Lob
//	@Column(name = "M_DESC")
//	@Comment(value = "간단소개")
//	private String description;
	
	public Member(String loginId, String password, String name) {
		this.loginId = loginId;
		this.password = password;
		this.name = name;
	}
	
}
