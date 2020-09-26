package jpashop.shop.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;
    private String question;
    private String answer;
    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public void addMember(Member member) {
        this.member = member;
    }

    public void addProduct(Product product) {
        this.product = product;
        this.product.getQuestions().add(this);
    }

    public void addAnswer(String answer){
        this.answer = answer;
    }

    @Builder(builderClassName = "createQuestion", builderMethodName = "createQuestion")
    public Question(Long id, String question, String answer, LocalDateTime createDate) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.createDate = createDate;
    }
}
