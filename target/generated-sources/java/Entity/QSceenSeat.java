package Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSceenSeat is a Querydsl query type for SceenSeat
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSceenSeat extends EntityPathBase<ScreenSeat> {

    private static final long serialVersionUID = 642238040L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSceenSeat sceenSeat = new QSceenSeat("sceenSeat");

    public final NumberPath<Integer> screenSeatId = createNumber("screenSeatId", Integer.class);

    public final QSeats seatId;

    public final QTickets ticket;

    public QSceenSeat(String variable) {
        this(ScreenSeat.class, forVariable(variable), INITS);
    }

    public QSceenSeat(Path<? extends ScreenSeat> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSceenSeat(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSceenSeat(PathMetadata metadata, PathInits inits) {
        this(ScreenSeat.class, metadata, inits);
    }

    public QSceenSeat(Class<? extends ScreenSeat> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.seatId = inits.isInitialized("seatId") ? new QSeats(forProperty("seatId"), inits.get("seatId")) : null;
        this.ticket = inits.isInitialized("ticket") ? new QTickets(forProperty("ticket"), inits.get("ticket")) : null;
    }

}

