package naero.naeroserver.review.service;

import naero.naeroserver.review.dto.ReviewDTO;
import naero.naeroserver.entity.inquiry.TblReview;
import naero.naeroserver.review.repository.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class ReviewService {

    private static final Logger log = LoggerFactory.getLogger(ReviewService.class);
    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ModelMapper modelMapper) {
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
    }

    // 사용자 기준 리뷰 조회
    public Page<ReviewDTO> getAllReviewsByUser(Integer userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TblReview> reviews = reviewRepository.findByUserId(userId, pageable);
        return reviews.map(review -> modelMapper.map(review, ReviewDTO.class));
    }

    // 상품 기준 리뷰 조회
    public Page<ReviewDTO> getAllReviewsByProduct(Integer productId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TblReview> reviews = reviewRepository.findByProductId(productId, pageable);
        return reviews.map(review -> modelMapper.map(review, ReviewDTO.class));
    }

    // 리뷰 상세 조회
    public ReviewDTO getReviewById(Integer reviewId) {
        TblReview review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰를 찾을 수 없습니다."));
        return modelMapper.map(review, ReviewDTO.class);
    }

    // 리뷰 등록
    @Transactional
    public String addReview(ReviewDTO reviewDTO) {
        TblReview review = modelMapper.map(reviewDTO, TblReview.class);
        review.setReviewDate(Instant.now());
        reviewRepository.save(review);
        return "리뷰 등록 성공";
    }

    // 리뷰 수정
    @Transactional
    public String updateReview(Integer reviewId, ReviewDTO reviewDTO) {
        TblReview review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰를 찾을 수 없습니다."));

        review.setReview(reviewDTO.getReview());
        review.setReviewImage(reviewDTO.getReivewImage());
        review.setReviewThumbnail(reviewDTO.getReviewThumbnail());
        review.setReviewRating(reviewDTO.getReviewRating());
        reviewRepository.save(review);

        return "리뷰 수정 성공";
    }

    // 리뷰 삭제
    @Transactional
    public String deleteReview(Integer reviewId) {
        TblReview review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰를 찾을 수 없습니다."));
        reviewRepository.delete(review);
        return "리뷰 삭제 성공";
    }
}