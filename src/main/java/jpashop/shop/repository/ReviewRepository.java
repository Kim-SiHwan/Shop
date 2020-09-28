package jpashop.shop.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import jpashop.shop.domain.Product;
import jpashop.shop.domain.QQuestion;
import jpashop.shop.domain.QReview;
import jpashop.shop.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findAllByProductId(Long productId);

}
