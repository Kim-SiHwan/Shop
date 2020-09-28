package jpashop.shop;

import jpashop.shop.domain.Member;
import jpashop.shop.domain.Product;
import jpashop.shop.domain.Question;
import jpashop.shop.domain.memberInfo.Address;
import jpashop.shop.domain.memberInfo.Info;
import jpashop.shop.dto.requestDto.QuestionRequestDto;
import jpashop.shop.repository.ProductRepository;
import jpashop.shop.repository.QuestionRepository;
import jpashop.shop.service.MemberService;
import jpashop.shop.service.QuestionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class QuestionServiceTest {

    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    ProductRepository productRepository;


    @Test
    public void 문의생성 (){
    //given
        Member member = createMember();
        createProduct();
        QuestionRequestDto questionRequestDto = new QuestionRequestDto();
        questionRequestDto.setProductId(1L);
        questionRequestDto.setQuestion("상품문의 드립니다.");
        questionRequestDto.setUserName(member.getUserName());
    //when
        Long qid= questionService.addQuestion(questionRequestDto);
        Question getQuestion = questionRepository.findById(qid).get();
    //then
        assertEquals("상품문의 드립니다.",getQuestion.getContent());

    }

    @Test
    public void 문의답변 (){
    //given
        Member member = createMember();
        createProduct();
        QuestionRequestDto questionRequestDto = new QuestionRequestDto();
        questionRequestDto.setProductId(1L);
        questionRequestDto.setQuestion("상품문의 드립니다.");
        questionRequestDto.setUserName(member.getUserName());
    //when
        Long qid= questionService.addQuestion(questionRequestDto);
        Question getQuestion = questionRepository.findById(qid).get();
        System.out.println(getQuestion.getAnswer());

        questionRequestDto.setQuestionId(qid);
        questionRequestDto.setAnswer("문의답변 드립니다.");
        questionService.answerQuestion(questionRequestDto);

        getQuestion = questionRepository.findById(qid).get();
    //then
        System.out.println(getQuestion.getAnswer());

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
