package h1y.my.portfolio.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * h1y.my.portfolio.dto.QExperiencedResponseDto is a Querydsl Projection type for ExperiencedResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QExperiencedResponseDto extends ConstructorExpression<ExperiencedResponseDto> {

    private static final long serialVersionUID = 489118365L;

    public QExperiencedResponseDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<Long> memberId, com.querydsl.core.types.Expression<String> memberName, com.querydsl.core.types.Expression<String> companyName, com.querydsl.core.types.Expression<String> startDate, com.querydsl.core.types.Expression<String> endDate, com.querydsl.core.types.Expression<String> department, com.querydsl.core.types.Expression<String> rank) {
        super(ExperiencedResponseDto.class, new Class<?>[]{long.class, long.class, String.class, String.class, String.class, String.class, String.class, String.class}, id, memberId, memberName, companyName, startDate, endDate, department, rank);
    }

}

