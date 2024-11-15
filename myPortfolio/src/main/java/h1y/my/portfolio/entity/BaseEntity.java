package h1y.my.portfolio.entity;

import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
public class BaseEntity extends BaseTimeEntity {

	@CreatedBy
	@Column(updatable = false)
	@Comment(value = "등록자")
	private String inUser;
	
	@LastModifiedBy
	@Comment(value = "수정자")
	private String upUser;
	
	@Column(length = 1)
	@Comment(value = "사용여부")
	private String useYn;
	
}
