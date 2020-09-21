package jpashop.shop.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String title;
    private String content;
    private String url;
    private String type;
    private int price;
    private int quantity;
    private LocalDateTime createDate;

    @Builder(builderClassName = "createProduct", builderMethodName = "createProduct")
    public Product(Long id, String title, String content, String url, String type, int price, int quantity, LocalDateTime createDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.url = url;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.createDate = createDate;
    }
}
