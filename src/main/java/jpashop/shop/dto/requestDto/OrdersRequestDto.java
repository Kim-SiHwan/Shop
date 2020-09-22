package jpashop.shop.dto.requestDto;

import jpashop.shop.domain.Orders;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrdersRequestDto {

    private String userName;
    private List<Long> productIds;
    private List<Integer> count;
    private List<Integer> price;

    public Orders toEntity(OrdersRequestDto requestDto){
        return Orders.createOrders()
                .build();
    }



}
