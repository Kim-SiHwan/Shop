package jpashop.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/shop")
public class ProductController {

    @GetMapping("/home")
    public String home(){
        return "shop/home";
    }
}
