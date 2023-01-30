package hello.itemservice.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class ItemDTO {
    private Long id;
    private String link;
    private String itemName;
    private Integer price;
    private Integer quantity;
    private String imgLink;
    private boolean alarm;
    @QueryProjection
    public ItemDTO(Long id, String link, String itemName, Integer price, Integer quantity, String imgLink, Boolean alarm) {
        this.id = id;
        this.link = link;
        this.itemName = itemName;
        this.price=price;
        this.quantity=quantity;
        this.imgLink=imgLink;
        this.alarm=alarm;
    }
}
