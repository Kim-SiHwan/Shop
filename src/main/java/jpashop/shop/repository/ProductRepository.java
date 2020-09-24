package jpashop.shop.repository;

import jpashop.shop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findTop10ByType(String type);

    List<Product> findTop20ByOrderByIdDesc();

}
