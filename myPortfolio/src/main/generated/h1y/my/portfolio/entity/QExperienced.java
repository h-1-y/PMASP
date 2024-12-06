package h1y.my.portfolio.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExperienced is a Querydsl query type for Experienced
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExperienced extends EntityPathBase<Experienced> {

    private static final long serialVersionUID = 1752844505L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExperienced experienced = new QExperienced("experienced");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath companyName = createString("companyName");

    public final StringPath department = createString("department");

    public final StringPath endDate = createString("endDate");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> inDate = _super.inDate;

    //inherited
    public final StringPath inUser = _super.inUser;

    public final QMember member;

    public final StringPath rank = createString("rank");

    public final StringPath startDate = createString("startDate");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> upDate = _super.upDate;

    //inherited
    public final StringPath upUser = _super.upUser;

    //inherited
    public final StringPath useYn = _super.useYn;

    public QExperienced(String variable) {
        this(Experienced.class, forVariable(variable), INITS);
    }

    public QExperienced(Path<? extends Experienced> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExperienced(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExperienced(PathMetadata metadata, PathInits inits) {
        this(Experienced.class, metadata, inits);
    }

    public QExperienced(Class<? extends Experienced> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

