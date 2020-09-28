package jpashop.shop.controller;

import jpashop.shop.dto.pagination.PageMaker;
import jpashop.shop.dto.pagination.PageVo;
import jpashop.shop.dto.responseDto.ProductResponseDto;
import jpashop.shop.dto.responseDto.QuestionResponseDto;
import jpashop.shop.service.ProductService;
import jpashop.shop.service.QuestionService;
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
    private final QuestionService questionService;

    @GetMapping("/main")
    public String home(Model model){
        List<List<ProductResponseDto>> typeList = productService.findAllByAllType();
        model.addAttribute("typeList",typeList);
        List<ProductResponseDto> itemList = productService.findTop20();
        model.addAttribute("itemList",itemList);
        return "/shop/main";
    }

    @GetMapping("/view")
    public String view(Long productId, Model model){
        ProductResponseDto responseDto = productService.viewProduct(productId);
        List<QuestionResponseDto> qList = questionService.findAllByProductId(productId);
        model.addAttribute("view",responseDto);
        model.addAttribute("qList", qList);
        return "/shop/view";
    }

    @GetMapping("/type")
    public String viewByType(PageVo vo,Model model){
        PageMaker list = productService.findAll(vo);
        model.addAttribute("list",list);
        model.addAttribute("vo",vo);
        return "/shop/type";
    }




}
