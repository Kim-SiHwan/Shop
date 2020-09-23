package jpashop.shop.dto.requestDto;

import jpashop.shop.domain.Cart;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartRequestDto {

    private String userName;
    private int count;
    private List<Long> productIds;


    public Cart toEntity(CartRequestDto requestDto){
        return Cart.createCart()
                .totalQuantity(count)
                .build();
    }

}
