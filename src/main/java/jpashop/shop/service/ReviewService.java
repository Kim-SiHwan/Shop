package jpashop.shop.service;

import jpashop.shop.domain.Member;
import jpashop.shop.domain.Product;
import jpashop.shop.domain.Review;
import jpashop.shop.dto.requestDto.ReviewRequestDto;
import jpashop.shop.repository.MemberRepository;
import jpashop.shop.repository.ProductRepository;
import jpashop.shop.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Long addReview (ReviewRequestDto requestDto){
        Member member = memberRepository.findByUserName(requestDto.getUserName());
        Product product = productRepository.findById(requestDto.getProductId()).get();
        Review review = requestDto.toEntity(requestDto);
        review.addMember(member);
        review.addProduct(product);
        reviewRepository.save(review);

        return review.getId();
    }

}
