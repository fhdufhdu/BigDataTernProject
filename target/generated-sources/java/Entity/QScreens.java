package Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QScreens is a Querydsl query type for Screens
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QScreens extends EntityPathBase<Screens> {

    private static final long serialVersionUID = 830542460L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QScreens screens = new QScreens("screens");

    public final QPeriod period;

    public final NumberPath<Integer> screenId = createNumber("screenId", Integer.class);

    public final QMovies screenMovie;

    public final QTheaters theater;

    public final QTickets tickets;

    public QScreens(String variable) {
        this(Screens.class, forVariable(variable), INITS);
    }

    public QScreens(Path<? extends Screens> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QScreens(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QScreens(PathMetadata metadata, PathInits inits) {
        this(Screens.class, metadata, inits);
    }

    public QScreens(Class<? extends Screens> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.period = inits.isInitialized("period") ? new QPeriod(forProperty("period")) : null;
        this.screenMovie = inits.isInitialized("screenMovie") ? new QMovies(forProperty("screenMovie")) : null;
        this.theater = inits.isInitialized("theater") ? new QTheaters(forProperty("theater")) : null;
        this.tickets = inits.isInitialized("tickets") ? new QTickets(forProperty("tickets"), inits.get("tickets")) : null;
    }

}

