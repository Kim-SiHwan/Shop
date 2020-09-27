package jpashop.shop.domain;


import jpashop.shop.domain.status.DeliveryStatus;
import jpashop.shop.domain.status.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
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

    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL)
    private List<OrderLine> orderLines = new ArrayList<>();

    public void addOrdersLine(OrderLine orderLine) {
        this.orderLines.add(orderLine);
        orderLine.addOrders(this);
    }

    public void addDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public void addMember(Member member) {
        this.member = member;
        this.member.getOrders().add(this);
    }


    public void cancel(){
        if(this.delivery.getDeliveryStatus() == DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        this.orderStatus = OrderStatus.CANCEL;
        for(OrderLine orderLine : orderLines){
            orderLine.orderCancel();
        }
    }

    @Builder(builderClassName = "createOrders", builderMethodName = "createOrders")
    public Orders() {
        this.createDate = LocalDateTime.now();
        this.orderStatus = OrderStatus.ORDER;
    }
}
