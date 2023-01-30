package hello.itemservice.domain;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * hello.itemservice.domain.QItemDTO is a Querydsl Projection type for ItemDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QItemDTO extends ConstructorExpression<ItemDTO> {

    private static final long serialVersionUID = -980497334L;

    public QItemDTO(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> link, com.querydsl.core.types.Expression<String> itemName, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<Integer> quantity, com.querydsl.core.types.Expression<String> imgLink, com.querydsl.core.types.Expression<Boolean> alarm) {
        super(ItemDTO.class, new Class<?>[]{long.class, String.class, String.class, int.class, int.class, String.class, boolean.class}, id, link, itemName, price, quantity, imgLink, alarm);
    }

}

