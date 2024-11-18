package h1y.my.portfolio.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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
	
	@BeforeEach
	public void setMember() {
		
		for ( int i=1; i<=100; i++ ) {
			Member member = new Member("loginId" + i, "password" + i, "name" + i);
			memberRepository.save(member);
		}
		
	}
	
	@Test
	public void saveTest() {
		
		List<Member> members = memberRepository.findAll();
		assertThat(members.size()).isEqualTo(100);
		
	}

	@Test
	public void findMemberTest() {
		
		Member findMember = memberRepository.findById(3L);
		assertThat(findMember.getLoginId()).isEqualTo("loginId3");
		
	}
	
}
