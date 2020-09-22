package jpashop.shop.dto.requestDto;

import jpashop.shop.domain.Review;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
public class ReviewRequestDto {

    @NotEmpty(message = "내용을 입력해주세요.")
    @Size(min = 1 , message = "1자이상 입력해주세요.")
    private String content;
    private String userName;
    private String productId;

    public Review toEntity(ReviewRequestDto requestDto){
        return Review.createReview()
                .content(requestDto.getContent())
                .createDate(LocalDateTime.now())
                .build();
    }
}
