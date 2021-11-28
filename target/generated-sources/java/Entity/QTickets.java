package Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTickets is a Querydsl query type for Tickets
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTickets extends EntityPathBase<Tickets> {

    private static final long serialVersionUID = 1876147164L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTickets tickets = new QTickets("tickets");

    public final BooleanPath isCanceled = createBoolean("isCanceled");

    public final QScreens screen;

    public final ListPath<ScreenSeat, QScreenSeat> screenSeats = this.<ScreenSeat, QScreenSeat>createList("screenSeats", ScreenSeat.class, QScreenSeat.class, PathInits.DIRECT2);

    public final NumberPath<Integer> ticketId = createNumber("ticketId", Integer.class);

    public final QUsers user;

    public QTickets(String variable) {
        this(Tickets.class, forVariable(variable), INITS);
    }

    public QTickets(Path<? extends Tickets> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTickets(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTickets(PathMetadata metadata, PathInits inits) {
        this(Tickets.class, metadata, inits);
    }

    public QTickets(Class<? extends Tickets> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.screen = inits.isInitialized("screen") ? new QScreens(forProperty("screen"), inits.get("screen")) : null;
        this.user = inits.isInitialized("user") ? new QUsers(forProperty("user"), inits.get("user")) : null;
    }

}

