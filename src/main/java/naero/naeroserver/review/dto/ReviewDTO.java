package naero.naeroserver.review.dto;

import java.time.LocalDateTime;

public class ReviewDTO {

    private Integer reviewId;
    private String review;
    private String reviewImage;
    private String reviewThumbnail;
    private int reviewRating;
    private LocalDateTime date;

    private Integer productId;
    private Integer userId;

    public ReviewDTO() {
    }

    public ReviewDTO(Integer reviewId, String review, String reviewImage, String reviewThumbnail, int reviewRating, LocalDateTime date, Integer productId, Integer userId) {
        this.reviewId = reviewId;
        this.review = review;
        this.reviewImage = reviewImage;
        this.reviewThumbnail = reviewThumbnail;
        this.reviewRating = reviewRating;
        this.date = date;
        this.productId = productId;
        this.userId = userId;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getReviewImage() {
        return reviewImage;
    }

    public void setReviewImage(String reviewImage) {
        this.reviewImage = reviewImage;
    }

    public String getReviewThumbnail() {
        return reviewThumbnail;
    }

    public void setReviewThumbnail(String reviewThumbnail) {
        this.reviewThumbnail = reviewThumbnail;
    }

    public int getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "reviewId=" + reviewId +
                ", review='" + review + '\'' +
                ", reviewImage='" + reviewImage + '\'' +
                ", reviewThumbnail='" + reviewThumbnail + '\'' +
                ", reviewRating=" + reviewRating +
                ", date=" + date +
                ", productId=" + productId +
                ", userId=" + userId +
                '}';
    }
}
