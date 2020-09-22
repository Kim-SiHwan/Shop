package jpashop.shop;

import jpashop.shop.domain.Member;
import jpashop.shop.domain.Orders;
import jpashop.shop.domain.Product;
import jpashop.shop.domain.memberInfo.Address;
import jpashop.shop.domain.memberInfo.Info;
import jpashop.shop.dto.requestDto.OrdersRequestDto;
import jpashop.shop.repository.OrdersRepository;
import jpashop.shop.repository.ProductRepository;
import jpashop.shop.service.MemberService;
import jpashop.shop.service.OrdersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrdersServiceTest {

    @Autowired
    private OrdersService ordersService;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ProductRepository productRepository;
    @Test
    public void 주문생성 (){
    //given
        Member member = createMember();
        createProduct();

        List<Long> pidList = Arrays.asList(1L,2L,3L);
        List<Integer> priceList = Arrays.asList(10000,20000,30000);
        List<Integer> quantityList = Arrays.asList(1,2,3);

        OrdersRequestDto ordersRequestDto = new OrdersRequestDto();
        ordersRequestDto.setCount(quantityList);
        ordersRequestDto.setPrice(priceList);
        ordersRequestDto.setProductIds(pidList);
        ordersRequestDto.setUserName(member.getUserName());
    //when
        Long orderId = ordersService.addOrders(ordersRequestDto);
        Orders getOrders = ordersRepository.findById(orderId).get();
    //then

        assertEquals("우엉",getOrders.getMember().getUserName());
        assertEquals(member.getAddress(),getOrders.getDelivery().getAddress());
        assertEquals(3,getOrders.getOrderLines().size());
        assertEquals(99,getOrders.getOrderLines().get(0).getProduct().getQuantity());
        System.out.println(getOrders.getId());
        System.out.println(getOrders.getCreateDate());
        System.out.println(getOrders.getMember().getUserName());
        System.out.println(getOrders.getDelivery().getAddress());
        System.out.println(getOrders.getOrderStatus());
        getOrders.getOrderLines().stream().forEach( o->System.out.println(o.getProduct().getTitle()));
        getOrders.getOrderLines().stream().forEach( o->System.out.println(o.getProduct().getQuantity()));
    }

    @Test
    public void 주문취소 (){
        //given
        Member member = createMember();
        createProduct();

        List<Long> pidList = Arrays.asList(1L,2L,3L);
        List<Integer> priceList = Arrays.asList(10000,20000,30000);
        List<Integer> quantityList = Arrays.asList(1,2,3);

        OrdersRequestDto ordersRequestDto = new OrdersRequestDto();
        ordersRequestDto.setCount(quantityList);
        ordersRequestDto.setPrice(priceList);
        ordersRequestDto.setProductIds(pidList);
        ordersRequestDto.setUserName(member.getUserName());
        //when
        Long orderId = ordersService.addOrders(ordersRequestDto);
        Orders getOrders = ordersRepository.findById(orderId).get();
        getOrders.cancel();
        //then

        assertEquals("우엉",getOrders.getMember().getUserName());
        assertEquals(member.getAddress(),getOrders.getDelivery().getAddress());
        assertEquals(3,getOrders.getOrderLines().size());
        assertEquals(100,getOrders.getOrderLines().get(0).getProduct().getQuantity());

        System.out.println(getOrders.getOrderStatus());
        getOrders.getOrderLines().stream().forEach( o->System.out.println(o.getProduct().getTitle()));
        getOrders.getOrderLines().stream().forEach( o->System.out.println(o.getProduct().getQuantity()));

    }


    public Member createMember(){
        Member member = Member.createMember()
                .userName("우엉")
                .password("1234")
                .address(new Address("시티","스트릿","집"))
                .info(new Info("010","naver.com"))
                .role("USER")
                .createDate(LocalDateTime.now())
                .build();
        memberService.join(member);
        return member;
    }

    public void createProduct(){
        for(int i=1; i<=3; i++){
            Product product = Product.createProduct()
                    .title("SampleTitle"+i)
                    .content("SampleContent"+i)
                    .createDate(LocalDateTime.now())
                    .price(i*10000)
                    .quantity(i*100)
                    .type("Tee")
                    .url("path")
                    .build();
            productRepository.save(product);
        }
    }


}
