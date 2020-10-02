package jpashop.shop;
import jpashop.shop.domain.*;
import jpashop.shop.domain.memberInfo.Address;
import jpashop.shop.domain.memberInfo.Info;
import jpashop.shop.dto.requestDto.CartRequestDto;
import jpashop.shop.dto.requestDto.QuestionRequestDto;
import jpashop.shop.dto.requestDto.ReviewRequestDto;
import jpashop.shop.repository.CartRepository;
import jpashop.shop.repository.ProductRepository;
import jpashop.shop.repository.QuestionRepository;
import jpashop.shop.repository.ReviewRepository;
import jpashop.shop.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertEquals;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class ReviewServiceTest {
    @Autowired
    ReviewService reviewService;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    ProductRepository productRepository;

    @Test
    public void 리뷰생성 (){
    //given
        Member member = createMember();
        createProduct();

        ReviewRequestDto reviewRequestDto = new ReviewRequestDto();
        reviewRequestDto.setContent("리뷰1");
        reviewRequestDto.setUserName("우엉");
        reviewRequestDto.setProductId(1L);

        Product product = productRepository.findById(1L).get();
    //when
        Boolean checkOrdered = reviewService.addReview(reviewRequestDto);
    //then
        assertEquals(false,checkOrdered);

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

    @Test
    public void dd(){
        for(int i=1; i<=20; i++){
            Random random = new Random();
            for(int j=1; j<= random.nextInt(15); j++) {

                System.out.println("INSERT INTO question (content, title , whether, create_date, answer , member_id, product_id )" +
                        " VALUES ('DummyQuestion', 'DummyTitle' , 'N' ,now(), '아직 답변이 존재하지 않습니다.' , 1 , "+i+"   );");
            }
        }
    }
}
