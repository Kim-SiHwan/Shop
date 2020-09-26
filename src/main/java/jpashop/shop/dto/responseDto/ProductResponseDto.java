package jpashop.shop.dto.responseDto;

import jpashop.shop.domain.Product;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProductResponseDto {
    private Long productId;
    private String title;
    private String content;
    private String url;
    private String type;
    private LocalDateTime createDate;
    private int reviewCount;
    private int questionCount;
    private int quantity;
    private int price;

    public ProductResponseDto(Product product) {
        productId = product.getId();
        title = product.getTitle();
        content = product.getContent();
        url = product.getUrl();
        type = product.getType();
        createDate = product.getCreateDate();
        reviewCount  = product.getReviews().size();
        questionCount = product.getQuestions().size();
        quantity = product.getQuantity();
        price = product.getPrice();
    }

}
