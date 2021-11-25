package Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDirectors is a Querydsl query type for Directors
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDirectors extends EntityPathBase<Directors> {

    private static final long serialVersionUID = -1800308196L;

    public static final QDirectors directors = new QDirectors("directors");

    public final QWorkers _super = new QWorkers(this);

    //inherited
    public final DateTimePath<java.util.Date> birth = _super.birth;

    public final StringPath birthPlace = createString("birthPlace");

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final NumberPath<Integer> workerId = _super.workerId;

    public QDirectors(String variable) {
        super(Directors.class, forVariable(variable));
    }

    public QDirectors(Path<? extends Directors> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDirectors(PathMetadata metadata) {
        super(Directors.class, metadata);
    }

}

