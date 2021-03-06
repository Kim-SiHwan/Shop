package jpashop.shop.service;

import jpashop.shop.domain.Cart;
import jpashop.shop.domain.Member;
import jpashop.shop.domain.Product;
import jpashop.shop.dto.requestDto.CartRequestDto;
import jpashop.shop.dto.responseDto.CartResponseDto;
import jpashop.shop.repository.CartRepository;
import jpashop.shop.repository.MemberRepository;
import jpashop.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Long addCart(CartRequestDto cartRequestDto){
        Member member = memberRepository.findByUserName(cartRequestDto.getUserName());
        Cart cart = cartRepository.findCartByMemberId(member.getId());
        Product product = productRepository.findById(cartRequestDto.getProductIds().get(0)).get();
        cart.addProducts(product);
        cart.addTotalQuantity(cartRequestDto.getCount());

        return cart.getId();
    }

    @Transactional
    public Long removeCart(List<Long> productIds, String userName){
        Member member = memberRepository.findByUserName(userName);
        Cart cart = cartRepository.findCartByMemberId(member.getId());
        List<Product> productList = productIds.stream()
                .map( pid -> productRepository.findById(pid).get())
                .collect(Collectors.toList());
        cart.removeProducts(productList);

        return cart.getId();
    }

    public CartResponseDto getCart(String userName){
        Member member = memberRepository.findByUserName(userName);
        Cart cart = cartRepository.findCartByMemberId(member.getId());
        CartResponseDto cartResponseDto = new CartResponseDto(cart);
        return cartResponseDto;
    }

}
