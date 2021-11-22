package Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDirectors is a Querydsl query type for Directors
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDirectors extends EntityPathBase<Directors> {

    private static final long serialVersionUID = -1800308196L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDirectors directors = new QDirectors("directors");

    public final StringPath birthPlace = createString("birthPlace");

    public final NumberPath<Integer> directorId = createNumber("directorId", Integer.class);

    public final QWorkers worker;

    public QDirectors(String variable) {
        this(Directors.class, forVariable(variable), INITS);
    }

    public QDirectors(Path<? extends Directors> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDirectors(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDirectors(PathMetadata metadata, PathInits inits) {
        this(Directors.class, metadata, inits);
    }

    public QDirectors(Class<? extends Directors> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.worker = inits.isInitialized("worker") ? new QWorkers(forProperty("worker")) : null;
    }

}

