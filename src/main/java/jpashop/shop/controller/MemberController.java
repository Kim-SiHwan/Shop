package jpashop.shop.controller;

import jpashop.shop.domain.Member;
import jpashop.shop.dto.requestDto.MemberRequestDto;
import jpashop.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String joinForm(Model model){
        MemberRequestDto memberRequestDto= new MemberRequestDto();
        model.addAttribute("memberForm",memberRequestDto);
        return "/members/createMember";
    }

    @PostMapping("/join")
    public String join(@Valid MemberRequestDto memberRequestDto, BindingResult result, Model model){
        model.addAttribute("memberForm",memberRequestDto);
        if(result.hasErrors()){
            return "/members/createMember";
        }
        Member member = memberRequestDto.toEntity(memberRequestDto);
        memberService.join(member);
        return "redirect:/shop/main";
    }
}
