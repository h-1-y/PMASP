package h1y.my.portfolio.repository;

import static h1y.my.portfolio.entity.QExperienced.experienced;
import static h1y.my.portfolio.entity.QMember.member;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import h1y.my.portfolio.dto.ExperiencedResponseDto;
import h1y.my.portfolio.dto.QExperiencedResponseDto;
import jakarta.persistence.EntityManager;

public class ExperiencedRepositoryCustomImpl implements ExperiencedRepositoryCustom {

	private final JPAQueryFactory queryFactory;
	
	public ExperiencedRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}
	
	@Override
	public Page<ExperiencedResponseDto> getExperienced(Long id, Pageable pageable) {
		
		List<ExperiencedResponseDto> result = queryFactory
				.select(
							new QExperiencedResponseDto(
										  experienced.id
										, member.id.as("memberId")
										, member.name.as("memberName")
										, experienced.companyName
										, experienced.startDate
										, experienced.endDate
										, experienced.department
										, experienced.rank
									)
						)
				.from(experienced)
				.leftJoin(experienced.member, member)
				.where(idEq(id))
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		JPAQuery<Long> countQuery = queryFactory
				.select(experienced.count())
				.from(experienced)
				.leftJoin(experienced.member, member)
				.where(idEq(id));
		
		return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchOne);
		
	}
	
	private BooleanExpression idEq(Long id) {
		return id != null ? member.id.eq(id) : null;
	}
	
}
