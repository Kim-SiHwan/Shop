package jpashop.shop.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import jpashop.shop.domain.Product;
import jpashop.shop.domain.QProduct;
import jpashop.shop.domain.QQuestion;
import jpashop.shop.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long>, QuerydslPredicateExecutor<Question> {
    List<Question> findAllByProductId(Long id);

    public default Predicate makePredicate(String type,String keyword){
        BooleanBuilder b= new BooleanBuilder();
        QQuestion question = QQuestion.question;

        b.and(question.id.gt(0));

        if(keyword==null){
            return b;
        }
        switch (type) {
            case "t":
                b.and(question.title.like("%" + keyword + "%"));
                break;
            case "w":
                b.and(question.member.userName.like("%" + keyword + "%"));
                break;
            case "c":
                b.and(question.content.like("%" + keyword + "%"));
                break;
            default:
                break;
        }
        return b;

    }

}
