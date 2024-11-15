package naero.naeroserver.entity.liked;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

@Entity
@Table(name = "tbl_liked_seller")
public class TblLikedSeller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_seller_id", nullable = false)
    private Integer likeSellerId;

    @NotNull
    @Column(name = "producer_id", nullable = false)
    private Integer producerId;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "brand_like_date")
    private Instant brandLikeDate;

    public Integer getLikeSellerId() {
        return likeSellerId;
    }

    public void setLikeSellerId(Integer id) {
        this.likeSellerId = id;
    }

    public Integer getProducerId() {
        return producerId;
    }

    public void setProducerId(Integer producerId) {
        this.producerId = producerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Instant getBrandLikeDate() {
        return brandLikeDate;
    }

    public void setBrandLikeDate(Instant brandLikeDate) {
        this.brandLikeDate = brandLikeDate;
    }

}