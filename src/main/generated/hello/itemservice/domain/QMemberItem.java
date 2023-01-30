package hello.itemservice.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberItem is a Querydsl query type for MemberItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberItem extends EntityPathBase<MemberItem> {

    private static final long serialVersionUID = 1489840527L;

    public static final QMemberItem memberItem = new QMemberItem("memberItem");

    public final BooleanPath alarm = createBoolean("alarm");

    public final NumberPath<Long> pid = createNumber("pid", Long.class);

    public final NumberPath<Long> uid = createNumber("uid", Long.class);

    public QMemberItem(String variable) {
        super(MemberItem.class, forVariable(variable));
    }

    public QMemberItem(Path<? extends MemberItem> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberItem(PathMetadata metadata) {
        super(MemberItem.class, metadata);
    }

}

