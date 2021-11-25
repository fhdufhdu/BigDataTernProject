package Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMovies is a Querydsl query type for Movies
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMovies extends EntityPathBase<Movies> {

    private static final long serialVersionUID = -1796345938L;

    public static final QMovies movies = new QMovies("movies");

    public final QCMbaseEntity _super = new QCMbaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final EnumPath<Genre> genre = createEnum("genre", Genre.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Integer> movieId = createNumber("movieId", Integer.class);

    public final StringPath name = createString("name");

    public final DateTimePath<java.time.LocalDateTime> openingDate = createDateTime("openingDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> runningTime = createNumber("runningTime", Integer.class);

    public final ListPath<MovieWorker, QMovieWorker> workers = this.<MovieWorker, QMovieWorker>createList("workers", MovieWorker.class, QMovieWorker.class, PathInits.DIRECT2);

    public QMovies(String variable) {
        super(Movies.class, forVariable(variable));
    }

    public QMovies(Path<? extends Movies> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMovies(PathMetadata metadata) {
        super(Movies.class, metadata);
    }

}

