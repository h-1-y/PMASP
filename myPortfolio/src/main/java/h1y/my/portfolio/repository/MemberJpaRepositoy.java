package h1y.my.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import h1y.my.portfolio.dto.MemberResponseDto;
import h1y.my.portfolio.entity.Member;

// Spring Data JPA
@Repository
public interface MemberJpaRepositoy extends JpaRepository<Member, Long> {
	
	Member findByLoginId(String loginId);
	
	@Query("select count(*) from Member m where m.loginId = :loginId")
	int findJoinLoginIdCheck(@Param("loginId") String loginId);
	
	@Query("select new h1y.my.portfolio.dto.MemberResponseDto(m.id, m.loginId, m.name, j.name, m.address.address, m.address.addressDetail, m.address.zipcode) "
			+ "from Member m left join m.job j where m.id = :id")
	MemberResponseDto getMember(@Param("id") Long id);
	
}
