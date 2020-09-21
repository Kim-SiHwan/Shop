package jpashop.shop.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_line_id")
    private Long id;
    private int totalPrice;
    private int totalQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder(builderClassName = "createOrderLine", builderMethodName = "createOrderLine")
    public OrderLine(Long id, int totalPrice, int totalQuantity) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
    }
}
