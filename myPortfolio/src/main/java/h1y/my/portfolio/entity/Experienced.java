package h1y.my.portfolio.entity;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
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
@SequenceGenerator(
		  name = "EXP_SEQ_GENERATOR"
		, sequenceName = "EXPERIENCED_SEQ"
		, initialValue = 1
		, allocationSize = 1
	  )
@Comment(value = "경력 테이블")
public class Experienced extends BaseEntity {

	@Id
	@GeneratedValue(
						  strategy = GenerationType.SEQUENCE
						, generator = "EXP_SEQ_GENERATOR"
				   )
	@Column(name = "EXP_ID")
	@Comment(value = "일련번호")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "m_id")
	@Comment(value = "회원 일련번호")
	private Member member;
	
	@Column(name = "COMPANY_NAME", length = 50, nullable = false)
	@Comment(value = "회사명")
	private String companyName;
	
	@Column(name = "START_DATE", length = 8, nullable = false)
	@Comment(value = "입사일")
	private String startDate;
	
	@Column(name = "END_DATE", length = 8)
	@Comment(value = "퇴사일")
	private String endDate;
	
	@Column(name = "DEPARTMENT", length = 20)
	@Comment(value = "부서")
	private String department;
	
	@Column(name = "RANK", length = 20)
	@Comment(value = "직급")
	private String rank;
	
}
