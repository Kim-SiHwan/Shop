package jpashop.shop.service;

import jpashop.shop.domain.Member;
import jpashop.shop.domain.Product;
import jpashop.shop.domain.Question;
import jpashop.shop.dto.requestDto.QuestionRequestDto;
import jpashop.shop.repository.MemberRepository;
import jpashop.shop.repository.ProductRepository;
import jpashop.shop.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Long addQuestion(QuestionRequestDto questionRequestDto){
        Member member = memberRepository.findByUserName(questionRequestDto.getUserName());
        Product product = productRepository.findById(questionRequestDto.getProductId()).get();
        Question question = questionRequestDto.toEntity(questionRequestDto);
        question.addMember(member);
        question.addProduct(product);
        questionRepository.save(question);
        return question.getId();
    }

    @Transactional
    public void answerQuestion(QuestionRequestDto questionRequestDto){
        Question question = questionRepository.findById(questionRequestDto.getQuestionId()).get();
        question.addAnswer(questionRequestDto.getAnswer());
    }



}
