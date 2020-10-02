package jpashop.shop.service;

import jpashop.shop.domain.Member;
import jpashop.shop.domain.Product;
import jpashop.shop.domain.Review;
import jpashop.shop.dto.requestDto.ReviewRequestDto;
import jpashop.shop.dto.responseDto.ReviewResponseDto;
import jpashop.shop.repository.MemberRepository;
import jpashop.shop.repository.ProductRepository;
import jpashop.shop.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private Boolean checkOrdered = false;

    public ReviewResponseDto.Result getReviews(Long productId) {
        List<Review> reviewList = reviewRepository.findAllByProductId(productId);
        List<ReviewResponseDto> list = reviewList.stream()
                .map(m -> new ReviewResponseDto(m))
                .collect(Collectors.toList());
        return new ReviewResponseDto.Result(list);
    }

    @Transactional
    public Boolean addReview(ReviewRequestDto requestDto) {
        Member member = memberRepository.findByUserName(requestDto.getUserName());
        Product product = productRepository.findById(requestDto.getProductId()).get();
        checkOrdered = false;
        member.getOrders().stream().forEach(o -> o.getOrderLines().stream().forEach(ol -> {
            if (ol.getProduct().getId() == product.getId()) {
                checkOrdered=true;
                Review review = requestDto.toEntity(requestDto);
                review.addMember(member);
                review.addProduct(product);
                reviewRepository.save(review);
            }
        }));

        return checkOrdered;
    }


}
