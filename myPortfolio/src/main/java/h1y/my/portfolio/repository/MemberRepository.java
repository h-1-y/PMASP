package h1y.my.portfolio.repository;

import static h1y.my.portfolio.entity.QJob.job;
import static h1y.my.portfolio.entity.QMember.member;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import h1y.my.portfolio.dto.MemberResponseDto;
import h1y.my.portfolio.dto.MemberSearchDto;
import h1y.my.portfolio.dto.QMemberResponseDto;
import jakarta.persistence.EntityManager;

@Repository
public class MemberRepository {

	private final EntityManager em;
	private final JPAQueryFactory queryFactory;
	
	public MemberRepository(EntityManager em) {
		this.em = em;
		this.queryFactory = new JPAQueryFactory(em);
	}
	
	public List<MemberResponseDto> getResponseMembers(MemberSearchDto memberSearchDto) {
		
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
				.where(
						  loginIdEq(memberSearchDto.getLoginId())
						, usernameEq(memberSearchDto.getUsername())
						, jobNameEq(memberSearchDto.getJobName())
					  )
				.fetch();
		
	}
	
	private BooleanExpression loginIdEq(String loginId) {
		return StringUtils.hasText(loginId) ? member.loginId.eq(loginId) : null;
	}
	
	private BooleanExpression usernameEq(String username) {
		return StringUtils.hasText(username) ? member.name.eq(username) : null;
	}
	
	private BooleanExpression jobNameEq(String jobName) {
		return StringUtils.hasText(jobName) ? job.name.eq(jobName) : null;
	}
	
}
