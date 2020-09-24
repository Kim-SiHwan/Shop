package jpashop.shop.service;

import jpashop.shop.domain.Member;
import jpashop.shop.domain.Product;
import jpashop.shop.domain.Question;
import jpashop.shop.dto.requestDto.QuestionRequestDto;
import jpashop.shop.dto.responseDto.ProductResponseDto;
import jpashop.shop.dto.responseDto.QuestionResponseDto;
import jpashop.shop.repository.MemberRepository;
import jpashop.shop.repository.ProductRepository;
import jpashop.shop.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public List<QuestionResponseDto> findAll(){
        List<Question> questionList = questionRepository.findAll();
        List<QuestionResponseDto> list = questionList.stream()
                .map( q -> new QuestionResponseDto(q))
                .collect(Collectors.toList());
        return list;
    }

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
