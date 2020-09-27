package jpashop.shop.dto.responseDto;

import jpashop.shop.domain.OrderLine;
import jpashop.shop.domain.Orders;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrdersResponseDto {
    private Long ordersId;
    private String orderState;
    private LocalDateTime createDate;
    private List<OrderLine> orderLines;

    public OrdersResponseDto(Orders orders){
        ordersId = orders.getId();
        orderState = orders.getOrderStatus().toString();
        createDate = orders.getCreateDate();
        orderLines = orders.getOrderLines();
    }
}
