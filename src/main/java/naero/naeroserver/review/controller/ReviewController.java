package naero.naeroserver.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import naero.naeroserver.common.Criteria;
import naero.naeroserver.common.PageDTO;
import naero.naeroserver.common.PagingResponseDTO;
import naero.naeroserver.common.ResponseDTO;
import naero.naeroserver.member.service.UserService;
import naero.naeroserver.review.dto.ReviewDTO;
import naero.naeroserver.review.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private static final Logger log = LoggerFactory.getLogger(ReviewController.class);
    private final ReviewService reviewService;
    private final UserService userService;

    @Autowired
    public ReviewController(ReviewService reviewService, UserService userService) {
        this.reviewService = reviewService;
        this.userService = userService;
    }

    // 사용자 기준 리뷰 조회 (페이징 처리)
    @Operation(summary = "(사용자) 리뷰 전체 조회", description = "리뷰 전체 조회가 진행됩니다.", tags = { "ReviewController" })
    @GetMapping("/username/{username}")
    public ResponseEntity<ResponseDTO> getReviewsByUser(
            @PathVariable String username,
            @RequestParam(name = "offset", defaultValue = "1") String offset) {

        int userId = userService.getUserIdFromUserName(username);

        int total = reviewService.getTotalQuestions(userId);

        Criteria cri = new Criteria(Integer.valueOf(offset), 2);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setData(reviewService.getAllReviewsByUser(userId, cri));
        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "사용자 리뷰 조회 성공", pagingResponseDTO));
    }


    // 상품 기준 리뷰 조회 (페이징 처리)
    @Operation(summary = "(판매자) 리뷰 전체 조회", description = "리뷰 전체 조회가 진행됩니다.", tags = { "ReviewController" })
    @GetMapping("/product/{productId}")
    public ResponseEntity<ResponseDTO> getReviewsByProduct(
            @PathVariable Integer productId,
            @RequestParam(name = "offset", defaultValue = "1") String offset) {


        int total = reviewService.getTotalQuestions(productId);

        Criteria cri = new Criteria(Integer.valueOf(offset), 2);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setData(reviewService.getAllReviewsByProduct(productId, cri));
        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "상품 리뷰 조회 성공", pagingResponseDTO));
    }

    // 리뷰 상세 조회
    @Operation(summary = "리뷰 상세 조회", description = "리뷰 상세 조회가 진행됩니다.", tags = { "ReviewController" })
    @GetMapping("/{reviewId}")
    public ResponseEntity<ResponseDTO> getReviewById(@PathVariable Integer reviewId) {

        ReviewDTO review = reviewService.getReviewById(reviewId);
        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "리뷰 상세 조회 성공", review));
    }

    // 리뷰 등록
    @Operation(summary = "리뷰 등록", description = "리뷰 등록이 진행됩니다.", tags = { "ReviewController" })
    @PostMapping("/{productId}")
    public ResponseEntity<ResponseDTO> addReview(
            @PathVariable Integer productId,
            @RequestPart("review") ReviewDTO reviewDTO,
            @RequestPart(value = "image", required = false) MultipartFile reviewImage) {

        String result = reviewService.addReview(productId, reviewDTO, reviewImage);
        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "리뷰 등록 성공", result));
    }

    // 리뷰 수정
    @Operation(summary = "리뷰 수정", description = "리뷰 수정이 진행됩니다.", tags = { "ReviewController" })
    @PutMapping("/{productId}/{reviewId}")
    public ResponseEntity<ResponseDTO> updateReview(
            @PathVariable Integer productId,
            @PathVariable Integer reviewId,
            @RequestPart("review") ReviewDTO reviewDTO,
            @RequestPart(value = "image", required = false) MultipartFile reviewImage) {

        String result = reviewService.updateReview(productId, reviewId, reviewDTO, reviewImage);
        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "리뷰 수정 성공", result));
    }

    // 리뷰 삭제
    @Operation(summary = "리뷰 삭제", description = "리뷰 삭제가 진행됩니다.", tags = { "ReviewController" })
    @DeleteMapping("/{productId}/{reviewId}")
    public ResponseEntity<ResponseDTO> deleteReview(@PathVariable Integer reviewId, @PathVariable Integer productId) {

        String result = reviewService.deleteReview(reviewId);
        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "리뷰 삭제 성공", result));
    }
}
