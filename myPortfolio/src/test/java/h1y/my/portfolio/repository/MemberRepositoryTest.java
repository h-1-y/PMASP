package h1y.my.portfolio.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import h1y.my.portfolio.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class MemberRepositoryTest {

	@Autowired
	EntityManager em;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	MemberJpaRepositoy memberJpaRepositoy;
	
	@BeforeEach
	public void setMember() {
		
		for ( int i=1; i<=100; i++ ) {
			Member member = new Member("loginId" + i, "password" + i, "name" + i);
			memberJpaRepositoy.save(member);
		}
		
	}
	
	@Test
	public void saveTest() {
		
		List<Member> members = memberJpaRepositoy.findAll();
		assertThat(members.size()).isEqualTo(100);
		
	}

	@Test
	public void findMemberTest() {
		
		Optional<Member> findMember = memberJpaRepositoy.findById(3L);
		assertThat(findMember.get().getLoginId()).isEqualTo("loginId3");
		
	}
	
}
