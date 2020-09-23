package jpashop.shop.dto.requestDto;

import jpashop.shop.domain.Question;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class QuestionRequestDto {
    private Long questionId;
    private String question;
    private String userName;
    private Long productId;
    private String answer;

    public Question toEntity(QuestionRequestDto requestDto){
        return Question.createQuestion()
                .question(requestDto.getQuestion())
                .createDate(LocalDateTime.now())
                .build();
    }
}
