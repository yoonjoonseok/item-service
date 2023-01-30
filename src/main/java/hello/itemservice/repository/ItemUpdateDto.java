package hello.itemservice.repository;
import lombok.Data;
@Data
public class ItemUpdateDto {

    //private String link;
    //private String itemName;
    //private Integer price;
    private Integer quantity;
    //private String imgLink;
    public ItemUpdateDto() {
    }
    public ItemUpdateDto(String link, String itemName, Integer price, Integer quantity, String imgLink) {
        //this.link = link;
        //this.itemName = itemName;
        //this.price = price;
        this.quantity = quantity;
        //this.imgLink = imgLink;
    }
}