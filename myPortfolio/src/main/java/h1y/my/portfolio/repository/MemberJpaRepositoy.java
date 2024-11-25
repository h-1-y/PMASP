package h1y.my.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import h1y.my.portfolio.entity.Member;

// Spring Data JPA
public interface MemberJpaRepositoy extends JpaRepository<Member, Long> {
	
	Member findByLoginId(String loginId);
	
	@Query("select count(*) from Member m where m.loginId = :loginId")
	int findJoinLoginIdCheck(@Param("loginId") String loginId);
	
}
