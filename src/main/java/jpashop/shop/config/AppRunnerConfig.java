package jpashop.shop.config;

import jpashop.shop.domain.Member;
import jpashop.shop.domain.memberInfo.Address;
import jpashop.shop.domain.memberInfo.Info;
import jpashop.shop.service.MemberService;
import jpashop.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppRunnerConfig implements ApplicationRunner {

    private final MemberService memberService;
    private final ProductService productService;
    private final PasswordEncoder pwEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        Member member1=Member.createMember()
                .userName("오이")
                .password(pwEncoder.encode("123"))
                .address(new Address("감자","토마토","양배추"))
                .createDate(LocalDateTime.now())
                .info(new Info("01012345678","naver.com"))
                .role("USER")
                .build();
        memberService.join(member1);

        Member member2=Member.createMember()
                .userName("배추")
                .password(pwEncoder.encode("123"))
                .address(new Address("감자","양상추","고구마"))
                .createDate(LocalDateTime.now())
                .info(new Info("01012345678","naver.com"))
                .role("USER")
                .build();
        memberService.join(member2);

        Member member=Member.createMember()
                .userName("관리자")
                .password(pwEncoder.encode("123"))
                .address(new Address("인천","남동","아파트"))
                .createDate(LocalDateTime.now())
                .info(new Info("01012345678","naver.com"))
                .role("ADMIN")
                .build();
        memberService.join(member);


    }
}
