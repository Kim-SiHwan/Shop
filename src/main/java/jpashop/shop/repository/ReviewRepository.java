package jpashop.shop.repository;

import jpashop.shop.domain.Product;
import jpashop.shop.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long>, QuerydslPredicateExecutor<Review> {
}
