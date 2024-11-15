package h1y.my.portfolio.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
public class BaseTimeEntity {

	@CreatedDate
	@Column(updatable = false)
	@Comment(value = "등록일")
	private LocalDateTime inDate;
	
	@LastModifiedDate
	@Comment(value = "수정일")
	private LocalDateTime upDate;
	
}
