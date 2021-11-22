package Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUsers is a Querydsl query type for Users
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUsers extends EntityPathBase<Users> {

    private static final long serialVersionUID = -189002691L;

    public static final QUsers users = new QUsers("users");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final StringPath city = createString("city");

    public final StringPath name = createString("name");

    public final StringPath street = createString("street");

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public final StringPath zipCode = createString("zipCode");

    public QUsers(String variable) {
        super(Users.class, forVariable(variable));
    }

    public QUsers(Path<? extends Users> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUsers(PathMetadata metadata) {
        super(Users.class, metadata);
    }

}

