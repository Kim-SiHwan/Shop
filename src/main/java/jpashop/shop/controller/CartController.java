package jpashop.shop.controller;

import jpashop.shop.domain.Cart;
import jpashop.shop.dto.requestDto.CartRequestDto;
import jpashop.shop.dto.responseDto.CartResponseDto;
import jpashop.shop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class CartController {
    private final CartService cartService;

    @Secured(value = {"ROLE_USER","ROLE_ADMIN"})
    @PostMapping("/add_cart")
    public String addCart(CartRequestDto cartRequestDto, RedirectAttributes rt){
        cartService.addCart(cartRequestDto);
        rt.addAttribute("productId",cartRequestDto.getProductIds().get(cartRequestDto.getProductIds().size()-1));
        return "redirect:/shop/view";
    }

    @Secured(value = {"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("/cart")
    public String viewCart(Principal principal, Model model){

        CartResponseDto cart = cartService.getCart(principal.getName());
        model.addAttribute("cart",cart);
        return "/shop/cart";
    }

    @DeleteMapping("/remove_cart")
    public String removeCart(CartRequestDto cartRequestDto){
        cartService.removeCart(cartRequestDto.getProductIds(),cartRequestDto.getUserName());
        return "redirect:/shop/cart";
    }
}
