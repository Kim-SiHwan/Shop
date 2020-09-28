package jpashop.shop.dto.responseDto;

import jpashop.shop.domain.Question;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class QuestionResponseDto {
    private Long questionId;
    private String title;
    private String question;
    private String answer;
    private String userName;
    private String check;
    private Long productId;
    private LocalDateTime createDate;

    public QuestionResponseDto(Question question) {
        this.question = question.getContent();
        questionId = question.getId();
        title = question.getTitle();
        answer = question.getAnswer();
        userName = question.getMember().getUserName();
        productId = question.getProduct().getId();
        createDate = question.getCreateDate();
        check = question.getWhether();

    }
}
