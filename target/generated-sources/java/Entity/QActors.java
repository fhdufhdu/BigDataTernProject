package Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QActors is a Querydsl query type for Actors
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QActors extends EntityPathBase<Actors> {

    private static final long serialVersionUID = 2143935881L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QActors actors = new QActors("actors");

    public final NumberPath<Integer> actorId = createNumber("actorId", Integer.class);

    public final NumberPath<Integer> height = createNumber("height", Integer.class);

    public final StringPath instagramId = createString("instagramId");

    public final QWorkers worker;

    public QActors(String variable) {
        this(Actors.class, forVariable(variable), INITS);
    }

    public QActors(Path<? extends Actors> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QActors(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QActors(PathMetadata metadata, PathInits inits) {
        this(Actors.class, metadata, inits);
    }

    public QActors(Class<? extends Actors> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.worker = inits.isInitialized("worker") ? new QWorkers(forProperty("worker")) : null;
    }

}

