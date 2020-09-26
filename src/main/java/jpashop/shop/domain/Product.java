package jpashop.shop.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
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

    @OneToMany(mappedBy = "product")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Question> questions = new ArrayList<>();

    public void changeProductText(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void changePrice(int price) {
        this.price = price;
    }

    public void addStockQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void removeStockQuantity(int quantity) {
        int tempStock = this.quantity -= quantity;
        if (tempStock >= 0) {
            this.quantity = tempStock;
        }
    }


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
