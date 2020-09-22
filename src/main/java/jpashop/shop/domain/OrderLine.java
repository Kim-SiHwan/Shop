package jpashop.shop.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private int orderPrice;
    private int orderQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public void addOrders(Orders orders) {
        this.orders = orders;
    }

    public void orderCancel(){
        this.getProduct().addStockQuantity(this.orderQuantity);
    }


    @Builder(builderClassName = "createOrderLine", builderMethodName = "createOrderLine")
    public OrderLine(int orderPrice, int orderQuantity, Product product) {
        this.orderPrice = orderPrice;
        this.orderQuantity = orderQuantity;
        this.product = product;
        this.product.removeStockQuantity(orderQuantity);
    }
}
