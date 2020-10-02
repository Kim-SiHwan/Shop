package jpashop.shop.controller;

import jpashop.shop.domain.Question;
import jpashop.shop.dto.pagination.PageMaker;
import jpashop.shop.dto.pagination.PageVo;
import jpashop.shop.dto.requestDto.QuestionRequestDto;
import jpashop.shop.dto.responseDto.QuestionResponseDto;
import jpashop.shop.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/questions")
    public String allQuestions(Model model, PageVo vo){
        PageMaker result = questionService.findAll(vo);
        model.addAttribute("list", result);
        model.addAttribute("vo",vo);
        return "/shop/questions";
    }

    @Secured(value = {"ROLE_USER","ROLE_ADMIN"})
    @PostMapping("/add_questions")
    public String addQuestion(QuestionRequestDto requestDto,RedirectAttributes rt){
        questionService.addQuestion(requestDto);
        rt.addAttribute("productId",requestDto.getProductId());
        return "redirect:/shop/view";
    }

    @Secured(value = {"ROLE_ADMIN"})
    @PutMapping("/update_questions")
    public String answerForQuestion(QuestionRequestDto requestDto){
        questionService.answerQuestion(requestDto);
        return "redirect:/shop/questions";
    }
}
