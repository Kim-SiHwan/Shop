package jpashop.shop.dto.requestDto;

import jpashop.shop.domain.Question;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class QuestionRequestDto {
    private String question;
    private String userName;
    private Long productId;

    public Question toEntity(QuestionRequestDto requestDto){
        return Question.createQuestion()
                .question(requestDto.getQuestion())
                .createDate(LocalDateTime.now())
                .build();
    }
}
