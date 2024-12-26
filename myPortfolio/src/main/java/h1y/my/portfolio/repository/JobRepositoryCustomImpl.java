package h1y.my.portfolio.repository;

import static h1y.my.portfolio.entity.QJob.job;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import h1y.my.portfolio.dto.JobResponseDto;
import h1y.my.portfolio.dto.QJobResponseDto;
import jakarta.persistence.EntityManager;

public class JobRepositoryCustomImpl implements JobRepositoryCustom {

	private final JPAQueryFactory queryFactory;
	
	public JobRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}
	
	@Override
	public Page<JobResponseDto> getJobs(Pageable pageable) {
		
		List<JobResponseDto> result = queryFactory
				.select(
							new QJobResponseDto(job.name.as("name"))
						)
				.from(job)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		JPAQuery<Long> countQuery = queryFactory
				.select(job.count())
				.from(job);
		
		return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchOne);
	}
	
}
