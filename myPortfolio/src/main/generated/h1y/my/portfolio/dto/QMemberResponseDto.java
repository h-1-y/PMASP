package h1y.my.portfolio.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * h1y.my.portfolio.dto.QMemberResponseDto is a Querydsl Projection type for MemberResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMemberResponseDto extends ConstructorExpression<MemberResponseDto> {

    private static final long serialVersionUID = 2017239531L;

    public QMemberResponseDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> loginId, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> jobName, com.querydsl.core.types.Expression<String> address, com.querydsl.core.types.Expression<String> addressDetail, com.querydsl.core.types.Expression<String> zipcode) {
        super(MemberResponseDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class, String.class, String.class}, id, loginId, name, jobName, address, addressDetail, zipcode);
    }

}

