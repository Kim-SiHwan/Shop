package jpashop.shop;

import jpashop.shop.domain.Cart;
import jpashop.shop.domain.Member;
import jpashop.shop.domain.Product;
import jpashop.shop.domain.memberInfo.Address;
import jpashop.shop.domain.memberInfo.Info;
import jpashop.shop.dto.requestDto.CartRequestDto;
import jpashop.shop.repository.CartRepository;
import jpashop.shop.repository.ProductRepository;
import jpashop.shop.service.CartService;
import jpashop.shop.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class CartServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CartService cartService;
    @Autowired
    CartRepository cartRepository;
    @Test
    public void 장바구니_상품_추가 (){
    //given

        Member member= createMember();
        createProduct();

        CartRequestDto cartRequestDto = new CartRequestDto();
        cartRequestDto.setCount(2);
        cartRequestDto.setProductIds(Arrays.asList(1L));
        cartRequestDto.setUserName("우엉");

    //when
        Long cartId = cartService.addCart(cartRequestDto);
        Cart getCart = cartRepository.findById(cartId).get();

    //then

        assertEquals(1,getCart.getProducts().size());
        assertEquals("SampleTitle1",getCart.getProducts().get(0).getTitle());

    }

    @Test
    public void 장바구니_상품_삭제 (){
    //given
        Member member = createMember();
        createProduct();
        CartRequestDto cartRequestDto = new CartRequestDto();
        cartRequestDto.setCount(2);
        cartRequestDto.setProductIds(Arrays.asList(1L));
        cartRequestDto.setUserName("우엉");

        CartRequestDto cartRequestDto2 = new CartRequestDto();
        cartRequestDto2.setCount(2);
        cartRequestDto2.setProductIds(Arrays.asList(2L));
        cartRequestDto2.setUserName("우엉");
    //when
        Long addCartId = cartService.addCart(cartRequestDto);
        cartService.addCart(cartRequestDto2);
        Cart getCart = cartRepository.findById(addCartId).get();
        Long removeCartId = cartService.removeCart(cartRequestDto);
        getCart = cartRepository.findById(removeCartId).get();
    //then
        assertEquals(1,getCart.getProducts().size());
        getCart.getProducts().stream().forEach(p-> System.out.println(p.getTitle()));



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
        //Member,Cart 동시에 생성.
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

