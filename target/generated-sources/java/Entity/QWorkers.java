package Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QWorkers is a Querydsl query type for Workers
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QWorkers extends EntityPathBase<Workers> {

    private static final long serialVersionUID = 429318570L;

    public static final QWorkers workers = new QWorkers("workers");

    public final DateTimePath<java.util.Date> birth = createDateTime("birth", java.util.Date.class);

    public final EnumPath<Dtype> dtype = createEnum("dtype", Dtype.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> workerId = createNumber("workerId", Integer.class);

    public QWorkers(String variable) {
        super(Workers.class, forVariable(variable));
    }

    public QWorkers(Path<? extends Workers> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWorkers(PathMetadata metadata) {
        super(Workers.class, metadata);
    }

}

