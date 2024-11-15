package naero.naeroserver.entity.inquiry;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import naero.naeroserver.entity.product.TblProduct;
import naero.naeroserver.entity.user.TblUser;

import java.time.Instant;

@Entity
@Table(name = "tbl_review")
public class TblReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "review_image")
    private String reviewImage;

    @Size(max = 255)
    @Column(name = "review_thumbnail")
    private String reviewThumbnail;

    @NotNull
    @Lob
    @Column(name = "review", nullable = false)
    private String review;

    @NotNull
    @Column(name = "review_rating", nullable = false)
    private Integer reviewRating;

    @Column(name = "review_date")
    private Instant reviewDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private TblProduct product;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private TblUser user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(Integer reviewRating) {
        this.reviewRating = reviewRating;
    }

    public Instant getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Instant reviewDate) {
        this.reviewDate = reviewDate;
    }

    public TblProduct getProduct() {
        return product;
    }

    public void setProduct(TblProduct product) {
        this.product = product;
    }

    public TblUser getUser() {
        return user;
    }

    public void setUser(TblUser user) {
        this.user = user;
    }

}