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
	
}
