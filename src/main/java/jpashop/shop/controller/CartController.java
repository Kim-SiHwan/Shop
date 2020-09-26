package jpashop.shop.controller;

import jpashop.shop.domain.Cart;
import jpashop.shop.dto.requestDto.CartRequestDto;
import jpashop.shop.dto.responseDto.CartResponseDto;
import jpashop.shop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class CartController {
    private final CartService cartService;

    @PostMapping("/add_cart")
    public void addCart(CartRequestDto cartRequestDto){
        cartService.addCart(cartRequestDto);
    }

    @GetMapping("/cart")
    public String viewCart(String userName, Model model){
        CartResponseDto cart = cartService.getCart(userName);
        model.addAttribute("cart",cart);
        return "/shop/cart";
    }
}
