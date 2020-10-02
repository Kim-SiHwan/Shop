package jpashop.shop.repository;

import jpashop.shop.domain.Orders;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {

    List<Orders> findAllByMember_Id(Long memberId);

}
