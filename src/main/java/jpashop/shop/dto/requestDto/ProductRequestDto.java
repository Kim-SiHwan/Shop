package jpashop.shop.dto.requestDto;

import jpashop.shop.domain.Product;
import lombok.Getter;

@Getter
public class ProductRequestDto {
    private Long productId;
    private String title;
    private String content;
    private String url;
    private String type;
    private int quantity;
    private int price;

    public Product toEntity(ProductRequestDto requestDto){
        return Product.createProduct()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .url(requestDto.getUrl())
                .type(requestDto.getType())
                .quantity(requestDto.getQuantity())
                .price(requestDto.getPrice())
                .build();
    }
}
