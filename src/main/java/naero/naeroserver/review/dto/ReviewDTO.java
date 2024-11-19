package naero.naeroserver.review.dto;

import java.time.LocalDateTime;

public class ReviewDTO {

    private Integer reviewId;
    private String review;
    private String reivewImage;
    private String reviewThumbnail;
    private int reviewRating;
    private LocalDateTime date;

    private int productId;
    private int userId;

    public ReviewDTO() {
    }

    public ReviewDTO(Integer reviewId, String review, String reivewImage, String reviewThumbnail, int reviewRating, LocalDateTime date, int productId, int userId) {
        this.reviewId = reviewId;
        this.review = review;
        this.reivewImage = reivewImage;
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

    public String getReivewImage() {
        return reivewImage;
    }

    public void setReivewImage(String reivewImage) {
        this.reivewImage = reivewImage;
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
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
                ", reivewImage='" + reivewImage + '\'' +
                ", reviewThumbnail='" + reviewThumbnail + '\'' +
                ", reviewRating=" + reviewRating +
                ", date=" + date +
                ", productId=" + productId +
                ", userId=" + userId +
                '}';
    }
}
