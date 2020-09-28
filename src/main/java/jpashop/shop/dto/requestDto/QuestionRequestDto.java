package jpashop.shop.dto.requestDto;

import jpashop.shop.domain.Question;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
public class QuestionRequestDto {

    private Long questionId;
    @NotEmpty(message = "내용을 입력해주세요.")
    @Size(min = 1 , message = "1자이상 입력해주세요.")
    private String question;
    private String title;
    private String userName;
    private Long productId;
    private String answer;

    public Question toEntity(QuestionRequestDto requestDto){
        return Question.createQuestion()
                .question(requestDto.getQuestion())
                .title(requestDto.getTitle())
                .createDate(LocalDateTime.now())
                .build();
    }
}
