package hello.itemservice.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QItemHistory is a Querydsl query type for ItemHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemHistory extends EntityPathBase<ItemHistory> {

    private static final long serialVersionUID = -2030304897L;

    public static final QItemHistory itemHistory = new QItemHistory("itemHistory");

    public final NumberPath<Long> pid = createNumber("pid", Long.class);

    public final NumberPath<Integer> stock = createNumber("stock", Integer.class);

    public final DateTimePath<java.sql.Timestamp> ts = createDateTime("ts", java.sql.Timestamp.class);

    public QItemHistory(String variable) {
        super(ItemHistory.class, forVariable(variable));
    }

    public QItemHistory(Path<? extends ItemHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItemHistory(PathMetadata metadata) {
        super(ItemHistory.class, metadata);
    }

}

