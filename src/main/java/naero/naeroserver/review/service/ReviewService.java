package naero.naeroserver.review.service;

import naero.naeroserver.common.Criteria;
import naero.naeroserver.review.dto.ReviewDTO;
import naero.naeroserver.entity.inquiry.TblReview;
import naero.naeroserver.review.repository.ReviewRepository;
import naero.naeroserver.util.FileUploadUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private static final Logger log = LoggerFactory.getLogger(ReviewService.class);
    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;

    @Value("${image.image-dir}")
    private String IMAGE_DIR;

    @Value("${image.image-url}")
    private String IMAGE_URL;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ModelMapper modelMapper) {
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
    }

    // 사용자 기준 리뷰 조회
    public Object getAllReviewsByUser(Integer userId, Criteria cri) {

        int index = cri.getPageNum() -1;
        int count = cri.getAmount();

        Pageable pageable = PageRequest.of(index, count, Sort.by("reviewId").descending());

        Page<TblReview> reviews = reviewRepository.findByUserId(userId, pageable);
        List<TblReview> reviewList = reviews.getContent();

        for(int i = 0; i<reviewList.size(); i++){
            reviewList.get(i).setReviewThumbnail(IMAGE_URL + reviewList.get(i).getReviewThumbnail());
        }

        return reviewList.stream().map(review -> modelMapper.map(review, ReviewDTO.class)).collect(Collectors.toList());
    }

    // 상품 기준 리뷰 조회
    public Object getAllReviewsByProduct(Integer productId, Criteria cri) {
        int index = cri.getPageNum() -1;
        int count = cri.getAmount();

        Pageable pageable = PageRequest.of(index, count, Sort.by("productId").descending());

        Page<TblReview> reviews = reviewRepository.findByProductId(productId, pageable);

        List<TblReview> reviewList = reviews.getContent();

        for(int i = 0; i<reviewList.size(); i++){
            reviewList.get(i).setReviewThumbnail(IMAGE_URL + reviewList.get(i).getReviewThumbnail());
        }

        return reviewList.stream().map(review -> modelMapper.map(review, ReviewDTO.class)).collect(Collectors.toList());
    }

    // 리뷰 상세 조회
    public ReviewDTO getReviewById(Integer reviewId) {
        TblReview review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰를 찾을 수 없습니다."));
        ReviewDTO dtreviewDTO = modelMapper.map(review, ReviewDTO.class);

        // 이미지 URL 추가
        if (dtreviewDTO.getReviewThumbnail() != null) {
            dtreviewDTO.setReviewThumbnail(IMAGE_URL + dtreviewDTO.getReviewThumbnail());
        }

        return dtreviewDTO;
    }

    // 리뷰 등록
    @Transactional
    public String addReview(Integer productId, ReviewDTO reviewDTO, MultipartFile reviewImage) {
        log.info("[ReviewService] addReview() 시작");

        String imageName = UUID.randomUUID().toString().replace("-", "");
        String savedFileName = null;
        String savedThumbnailName = null;

        try {
            if (reviewImage != null && !reviewImage.isEmpty()) {
                savedFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, reviewImage);
                savedThumbnailName = FileUploadUtils.saveThumbnailFile(IMAGE_DIR, savedFileName);

                reviewDTO.setReviewImage(savedFileName);
                reviewDTO.setReviewThumbnail(savedThumbnailName);
                log.info("[ReviewService] 이미지 저장 완료: {}", savedFileName);
            }

            TblReview review = modelMapper.map(reviewDTO, TblReview.class);
            review.setReviewDate(Instant.now());
            reviewRepository.save(review);

            log.info("[ReviewService] addReview() 종료");
            return "리뷰 등록 성공";
        } catch (Exception e) {
            if (savedFileName != null) {
                FileUploadUtils.deleteFile(IMAGE_DIR, savedFileName);
            }
            throw new RuntimeException("리뷰 등록 중 오류 발생", e);
        }
    }

    @Transactional
    public String updateReview(Integer productId, Integer reviewId, ReviewDTO reviewDTO, MultipartFile reviewImage) {
        log.info("[ReviewService] updateReview() 시작");

        TblReview review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰를 찾을 수 없습니다."));

        if (productId == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }

        String oldImageName = review.getReviewThumbnail();
        String savedFileName = null;

        try {
            System.out.println("productId" + productId);
            review.setProductId(productId); // productId 설정
            review.setReview(reviewDTO.getReview());
            review.setReviewRating(reviewDTO.getReviewRating());

            if (reviewImage != null && !reviewImage.isEmpty()) {
                String imageName = UUID.randomUUID().toString().replace("-", "");
                savedFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, reviewImage);
                String savedThumbnailName = FileUploadUtils.saveThumbnailFile(IMAGE_DIR, savedFileName);

                review.setReviewImage(savedFileName);
                review.setReviewThumbnail(savedThumbnailName);

                if (oldImageName != null) {
                    FileUploadUtils.deleteFile(IMAGE_DIR, oldImageName);
                }
                log.info("[ReviewService] 이미지 업데이트 완료: {}", savedFileName);
            }

            reviewRepository.save(review);
            log.info("[ReviewService] updateReview() 종료");
            return "리뷰 수정 성공";
        } catch (IOException e) {
            if (savedFileName != null) {
                FileUploadUtils.deleteFile(IMAGE_DIR, savedFileName);
            }
            throw new RuntimeException("리뷰 수정 중 오류 발생", e);
        }
    }

    // 리뷰 삭제
    @Transactional
    public String deleteReview(Integer reviewId) {
        log.info("[ReviewService] deleteReview() 시작");

        TblReview review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰를 찾을 수 없습니다."));

        String imageName = review.getReviewThumbnail();
        if (imageName != null) {
            FileUploadUtils.deleteFile(IMAGE_DIR, imageName);
        }

        reviewRepository.delete(review);

        log.info("[ReviewService] deleteReview() 종료");
        return "리뷰 삭제 성공";
    }

    public int getTotalQuestions(int userId) {
        return reviewRepository.countByUserId(userId);
    }
}
