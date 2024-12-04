package h1y.my.portfolio.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
		  name = "JOB_SEQ_GENERATOR"
		, sequenceName = "JOB_SEQ"
		, initialValue = 1
		, allocationSize = 1
	  )
@Comment(value = "직군 테이블")
public class Job extends BaseEntity {

	@Id
	@GeneratedValue(
			  strategy = GenerationType.SEQUENCE
			, generator = "JOB_SEQ_GENERATOR"
	   )
	@Column(name = "JOB_ID")
	@Comment(value = "일련번호")
	private Long id;
	
	@Column(name = "JOB_NAME")
	@Comment(value = "직군명")
	private String name;

//	@OneToMany(mappedBy = "job")
//	private List<Member> members = new ArrayList<>();
	
	public Job(String name) {
		super();
		this.name = name;
	}
		
}
