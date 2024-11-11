package naero.naeroserver.entity.liked;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import naero.naeroserver.entity.user.TblProducer;
import naero.naeroserver.entity.user.TblUser;

import java.time.Instant;

@Entity
@Table(name = "tbl_liked_seller")
public class TblLikedSeller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likeSeller_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "producer_id", nullable = false, referencedColumnName = "producer_id")
    private TblProducer producer;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private TblUser user;

    @NotNull
    @Column(name = "brand_like_date", nullable = false)
    private Instant brandLikeDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TblProducer getProducer() {
        return producer;
    }

    public void setProducer(TblProducer producer) {
        this.producer = producer;
    }

    public TblUser getUser() {
        return user;
    }

    public void setUser(TblUser user) {
        this.user = user;
    }

    public Instant getBrandLikeDate() {
        return brandLikeDate;
    }

    public void setBrandLikeDate(Instant brandLikeDate) {
        this.brandLikeDate = brandLikeDate;
    }

}