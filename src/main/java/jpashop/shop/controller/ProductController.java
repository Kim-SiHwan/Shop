package jpashop.shop.controller;

import jpashop.shop.dto.responseDto.ProductResponseDto;
import jpashop.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/home")
    public String home(Model model){
        List<List<ProductResponseDto>> typeList = productService.findAllByType();
        model.addAttribute("typeList",typeList);
        List<ProductResponseDto> itemList = productService.findTop20();
        model.addAttribute("itemList",itemList);
        return "shop/home";
    }


    @GetMapping("/view")
    public String view(Long productId, Model model){
        ProductResponseDto responseDto = productService.viewProduct(productId);
        model.addAttribute("view",responseDto);
        return "/shop/view";
    }


}
