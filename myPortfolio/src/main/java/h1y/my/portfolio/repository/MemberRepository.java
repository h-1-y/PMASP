package h1y.my.portfolio.repository;

import static h1y.my.portfolio.entity.QJob.job;
import static h1y.my.portfolio.entity.QMember.member;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import h1y.my.portfolio.dto.MemberResponseDto;
import h1y.my.portfolio.dto.QMemberResponseDto;
import h1y.my.portfolio.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class MemberRepository {

	private final EntityManager em;
	private final JPAQueryFactory queryFactory;
	
	public MemberRepository(EntityManager em) {
		this.em = em;
		this.queryFactory = new JPAQueryFactory(em);
	}
	
	public List<MemberResponseDto> getResponseMembers() {
		
		return queryFactory
				.select(
							new QMemberResponseDto(
										  member.id.as("id")
										, member.loginId.as("loginId")
										, member.name.as("name")
										, job.name.as("jobName")
										, member.address.address.as("address")
										, member.address.addressDetail.as("addressDetail")
										, member.address.zipcode.as("zipcode")
									)
						)
				.from(member)
				.leftJoin(member.job, job)
				.fetch();
		
	}
	
}
