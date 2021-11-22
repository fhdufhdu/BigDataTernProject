package Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTheaters is a Querydsl query type for Theaters
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTheaters extends EntityPathBase<Theaters> {

    private static final long serialVersionUID = 1486939181L;

    public static final QTheaters theaters = new QTheaters("theaters");

    public final StringPath floor = createString("floor");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> theaterId = createNumber("theaterId", Integer.class);

    public QTheaters(String variable) {
        super(Theaters.class, forVariable(variable));
    }

    public QTheaters(Path<? extends Theaters> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTheaters(PathMetadata metadata) {
        super(Theaters.class, metadata);
    }

}

