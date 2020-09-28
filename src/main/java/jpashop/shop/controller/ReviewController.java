package jpashop.shop.controller;

import jpashop.shop.dto.requestDto.ReviewRequestDto;
import jpashop.shop.dto.responseDto.ReviewResponseDto;
import jpashop.shop.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews/**")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewResponseDto.Result getReviews(Long productId){
        return reviewService.getReviews(productId);
    }

    @GetMapping("/{productId}")
    public ResponseEntity getAllByProductId(@PathVariable("productId") Long productId){
        log.info("ccc:"+productId);
        return new ResponseEntity(getReviews(productId), HttpStatus.OK);
    }

    @PostMapping("/{productId}")
    public ResponseEntity addReview(@PathVariable("productId") Long productId,
                                    @RequestBody ReviewRequestDto reviewRequestDto){
        reviewService.addReview(reviewRequestDto);
        return new ResponseEntity(getReviews(productId), HttpStatus.CREATED);
    }

}
