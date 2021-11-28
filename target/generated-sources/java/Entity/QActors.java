package Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QActors is a Querydsl query type for Actors
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QActors extends EntityPathBase<Actors> {

    private static final long serialVersionUID = 2143935881L;

    public static final QActors actors = new QActors("actors");

    public final QWorkers _super = new QWorkers(this);

    //inherited
    public final DateTimePath<java.util.Date> birth = _super.birth;

    public final NumberPath<Integer> height = createNumber("height", Integer.class);

    public final StringPath instagramId = createString("instagramId");

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final NumberPath<Integer> workerId = _super.workerId;

    public QActors(String variable) {
        super(Actors.class, forVariable(variable));
    }

    public QActors(Path<? extends Actors> path) {
        super(path.getType(), path.getMetadata());
    }

    public QActors(PathMetadata metadata) {
        super(Actors.class, metadata);
    }

}

