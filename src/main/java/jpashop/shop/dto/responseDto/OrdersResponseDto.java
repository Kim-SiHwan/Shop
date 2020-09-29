package jpashop.shop.dto.responseDto;

import jpashop.shop.domain.OrderLine;
import jpashop.shop.domain.Orders;
import jpashop.shop.domain.memberInfo.Address;
import jpashop.shop.domain.memberInfo.Info;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrdersResponseDto {
    private Long ordersId;
    private String orderState;
    private LocalDateTime createDate;
    private List<OrderLine> orderLines;
    private Address delivery;
    private String deliveryState;
    private Address memberAddress;
    private Info memberInfo;

    public OrdersResponseDto(Orders orders){
        ordersId = orders.getId();
        orderState = orders.getOrderStatus().toString();
        createDate = orders.getCreateDate();
        orderLines = orders.getOrderLines();
        delivery = orders.getDelivery().getAddress();
        deliveryState = orders.getDelivery().getDeliveryStatus().toString();
        memberAddress = orders.getMember().getAddress();
        memberInfo = orders.getMember().getInfo();
    }
}
