package jpashop.shop.config;

import jpashop.shop.domain.*;
import jpashop.shop.domain.memberInfo.Address;
import jpashop.shop.domain.memberInfo.Info;
import jpashop.shop.repository.ProductRepository;
import jpashop.shop.repository.ReviewRepository;
import jpashop.shop.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppRunnerConfig implements ApplicationRunner {

    private final MemberService memberService;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final QuestionService questionService;
    private final OrdersService ordersService;
    private final PasswordEncoder pwEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Member member1 = Member.createMember()
                .userName("김시환")
                .password(pwEncoder.encode("1234"))
                .address(new Address("인천", "무내미", "1234"))
                .createDate(LocalDateTime.now())
                .info(new Info("01012345678", "naver.com"))
                .role("USER")
                .build();
        memberService.join(member1);

        Member member2 = Member.createMember()
                .userName("테스트")
                .password(pwEncoder.encode("1234"))
                .address(new Address("테스트1", "테스트2", "테스트3"))
                .createDate(LocalDateTime.now())
                .info(new Info("01012345678", "naver.com"))
                .role("USER")
                .build();
        memberService.join(member2);

        Member member3 = Member.createMember()
                .userName("관리자")
                .password(pwEncoder.encode("1234"))
                .address(new Address("인천", "남동", "아파트"))
                .createDate(LocalDateTime.now())
                .info(new Info("01012345678", "naver.com"))
                .role("ADMIN")
                .build();
        memberService.join(member3);


    }
}
