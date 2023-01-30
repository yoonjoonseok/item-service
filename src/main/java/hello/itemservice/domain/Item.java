package hello.itemservice.domain;
import hello.itemservice.web.item.GetResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String link;
    private String itemName;
    private Integer price;
    private Integer quantity;
    private String imgLink;

    /*public Item(String itemName, String link, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.link = link;
        this.price = price;
        this.quantity = quantity;
    }*/
    public Item(String link) {
        this.link = link;
    }

    public void setOthers(){
        Document doc = GetResponse.getResponse(this.link);

        Elements product = (Elements) doc.select("a[href=#zoom-layer]").select("img[src$=.jpg]");

        this.itemName = product.attr("title");
        this.price =  Integer.parseInt(doc.select("input[name=set_goods_price]").val());
        this.imgLink = product.attr("src");
        this.quantity = Integer.parseInt(doc.select("input[name=set_goods_stock]").val());
    }
    public Integer updateStock() {
        Document doc = GetResponse.getResponse(this.link);
        Elements product = (Elements) doc.select("a[href=#zoom-layer]").select("img[src$=.jpg]");
        return Integer.parseInt(doc.select("input[name=set_goods_stock]").val());
    }
}

