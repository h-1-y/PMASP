package h1y.my.portfolio.repository;

import static h1y.my.portfolio.entity.QJob.job;
import static h1y.my.portfolio.entity.QMember.member;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import h1y.my.portfolio.dto.MemberResponseDto;
import h1y.my.portfolio.dto.MemberSearchDto;
import h1y.my.portfolio.dto.QMemberResponseDto;
import jakarta.persistence.EntityManager;

public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

	private final JPAQueryFactory queryFactory;
	
	public MemberRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}
	
	@Override
	public Page<MemberResponseDto> getMembers(MemberSearchDto memberSearchDto, Pageable pageable) {
		
		List<MemberResponseDto> result = queryFactory
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
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		JPAQuery<Long> countQuery = queryFactory
				.select(member.count())
				.from(member)
				.leftJoin(member.job, job)
				.where(
						  loginIdEq(memberSearchDto.getLoginId())
						, usernameEq(memberSearchDto.getUsername())
						, jobNameEq(memberSearchDto.getJobName())
					  );
		
		return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchOne);
		
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
