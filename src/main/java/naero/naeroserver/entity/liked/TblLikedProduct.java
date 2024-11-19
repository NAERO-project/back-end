package naero.naeroserver.entity.liked;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

@Entity
@Table(name = "tbl_liked_product")
public class TblLikedProduct {
    @Id
    @Column(name = "like_id", nullable = false)
    private Integer likeId;

    @NotNull
    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "product_like_date")
    private Instant productLikeDate;

    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer id) {
        this.likeId = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Instant getProductLikeDate() {
        return productLikeDate;
    }

    public void setProductLikeDate(Instant productLikeDate) {
        this.productLikeDate = productLikeDate;
    }

}