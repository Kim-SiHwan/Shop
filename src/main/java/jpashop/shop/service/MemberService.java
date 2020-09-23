package jpashop.shop.service;

import jpashop.shop.domain.Cart;
import jpashop.shop.domain.Member;
import jpashop.shop.repository.CartRepository;
import jpashop.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;

    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        Cart cart = Cart.createCart()
                .build();
        cart.addMember(member);
        cartRepository.save(cart);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Member getMember = memberRepository.findByUserName(member.getUserName());
        if(getMember!=null){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public Member findOne(Long memberId){
        return memberRepository.findById(memberId).get();
    }

}
