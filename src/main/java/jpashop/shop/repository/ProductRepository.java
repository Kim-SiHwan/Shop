package jpashop.shop.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import jpashop.shop.domain.Product;
import jpashop.shop.domain.QProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>, QuerydslPredicateExecutor<Product> {

    List<Product> findTop10ByType(String type);

    List<Product> findTop20ByOrderByIdDesc();

    public default Predicate makePredicate(String type,String keyword){
        BooleanBuilder b= new BooleanBuilder();
        QProduct product = QProduct.product;
        b.and(product.id.gt(0));
        b.and(product.type.like("%"+type+"%"));

        if(keyword==null){
            return b;
        }else{
            b.and(product.title.like("%"+keyword+"%"));
        }
        return b;

    }

}
