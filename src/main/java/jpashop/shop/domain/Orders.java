package jpashop.shop.domain;


import jpashop.shop.domain.status.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Long id;

    private LocalDateTime createDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "orders")
    private List<OrderLine> orderLines;

    @Builder(builderClassName = "createOrders", builderMethodName = "createOrders")
    public Orders(Long id, LocalDateTime createDate, OrderStatus orderStatus, Delivery delivery) {
        this.id = id;
        this.createDate = createDate;
        this.orderStatus = orderStatus;
        this.delivery = delivery;
    }
}
