package jpashop.shop.dto.responseDto;

import jpashop.shop.domain.Cart;
import jpashop.shop.domain.Product;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CartResponseDto {
    private List<ProductResponseDto> products;
    private int totalCount;
    private int totalPrice;

    public CartResponseDto (Cart cart){
        List<Product> productList= cart.getProducts();
        products= productList.stream().map(m->new ProductResponseDto(m)).collect(Collectors.toList());
        totalCount = cart.getTotalQuantity();
        totalPrice = cart.getTotalPrice();

    }
}
