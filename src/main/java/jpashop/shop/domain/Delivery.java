package jpashop.shop.domain;


import jpashop.shop.domain.memberInfo.Address;
import jpashop.shop.domain.status.DeliveryStatus;
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
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    private LocalDateTime createDate;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @Builder(builderClassName = "createDelivery", builderMethodName = "createDelivery")
    public Delivery(Long id, LocalDateTime createDate, Address address, DeliveryStatus deliveryStatus) {
        this.id = id;
        this.createDate = createDate;
        this.address = address;
        this.deliveryStatus = deliveryStatus;
    }
}
