package h1y.my.portfolio.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Job extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "JOB_ID")
	@Comment(value = "일련번호")
	private Long id;
	
	@Column(name = "JOB_NAME")
	@Comment(value = "직군명")
	private String name;
	
	@OneToMany(mappedBy = "job")
	private List<Member> members = new ArrayList<>();
	
}
