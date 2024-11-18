package h1y.my.portfolio.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import h1y.my.portfolio.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class MemberRepository {

	private final EntityManager em;
//	private final JPAQueryFactory queryFactory;
	
	public MemberRepository(EntityManager em) {
		this.em = em;
	}
	
	@Transactional
	public Long save(Member member) {
		em.persist(member); 
		return member.getId(); 
	}
	
	public Member findById(Long id) {
		Member findMember = em.find(Member.class, id);
		return findMember;
	}
	
	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class).getResultList();
	}
	
}
