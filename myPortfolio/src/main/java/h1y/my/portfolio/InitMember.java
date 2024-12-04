package h1y.my.portfolio;

import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import h1y.my.portfolio.entity.Job;
import h1y.my.portfolio.entity.Member;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Profile("local")
@Component
@RequiredArgsConstructor
public class InitMember {

	private final InitMemberService initMemberService;
	
	@PostConstruct
	public void init() {
		initMemberService.init();
	}
	
	@Component
	static class InitMemberService {
		
		@PersistenceContext
		private EntityManager em;
		
		@Transactional
		public void init() {
			
			for ( int i=1; i<=10; i++ ) {
				Member member = new Member("loginId" + i, "password" + i, "name" + i);
				em.persist(member);
			}
			
			Job job1 = new Job("Developer");
			Job job2 = new Job("Designer");
			
			em.persist(job1);
			em.persist(job2);
			
		}
		
	}
	
}
