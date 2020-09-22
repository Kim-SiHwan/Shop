package jpashop.shop.dto.responseDto;

import jpashop.shop.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewResponseDto {
    private Long id;
    private String content;
    private String userName;
    private LocalDateTime createDate;

    public ReviewResponseDto(Review review){
        id= review.getId();
        content = review.getContent();;
        userName = review.getMember().getUserName();
        createDate =review.getCreateDate();
    }

    @Data
    @AllArgsConstructor
    public static class Result<T>{
        private T data;
    }

}
