package jpashop.shop;

import jpashop.shop.domain.Member;
import jpashop.shop.domain.memberInfo.Address;
import jpashop.shop.domain.memberInfo.Info;
import jpashop.shop.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import static org.junit.Assert.*;


@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void 회원가입(){
    //given
        Member member = Member.createMember()
                .userName("우엉")
                .password("1234")
                .address(new Address("시티","스트릿","집"))
                .info(new Info("010","naver.com"))
                .role("USER")
                .createDate(LocalDateTime.now())
                .build();
        memberService.join(member);
    //when
        Long memberId = member.getId();
    //then
        assertEquals(member,memberService.findOne(memberId));

    }

    @Test(expected = IllegalStateException.class)
    public void 중복확인 (){
    //given
        Member member1 = Member.createMember()
                .userName("우엉")
                .password("1234")
                .address(new Address("시티","스트릿","집"))
                .info(new Info("010","naver.com"))
                .role("USER")
                .createDate(LocalDateTime.now())
                .build();
        memberService.join(member1);

    //when
        Member member2 = Member.createMember()
                .userName("우엉")
                .password("1234")
                .address(new Address("시티","스트릿","집"))
                .info(new Info("010","naver.com"))
                .role("USER")
                .createDate(LocalDateTime.now())
                .build();
        memberService.join(member2);
    //then
        fail("여기로 오면 안돼");
    }
}
