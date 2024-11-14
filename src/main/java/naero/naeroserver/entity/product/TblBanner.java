package naero.naeroserver.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import naero.naeroserver.entity.user.TblProducer;
import naero.naeroserver.entity.user.TblUser;

import java.time.Instant;

@Entity
@Table(name = "tbl_banner")
public class TblBanner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banner_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "banner_thumbnail")
    private String bannerThumbnail;

    @Size(max = 255)
    @Column(name = "banner_img")
    private String bannerImg;

    @Size(max = 250)
    @Column(name = "banner_url", length = 250)
    private String bannerUrl;

    @Column(name = "banner_create_at")
    private Instant bannerCreateAt;

    @Column(name = "banner_delete_at")
    private Instant bannerDeleteAt;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "producer_id", nullable = false)
    private TblProducer producer;

    @Column(name = "banner_accept_at")
    private Instant bannerAcceptAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approver_id")
    private TblUser approver;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBannerThumbnail() {
        return bannerThumbnail;
    }

    public void setBannerThumbnail(String bannerThumbnail) {
        this.bannerThumbnail = bannerThumbnail;
    }

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public Instant getBannerCreateAt() {
        return bannerCreateAt;
    }

    public void setBannerCreateAt(Instant bannerCreateAt) {
        this.bannerCreateAt = bannerCreateAt;
    }

    public Instant getBannerDeleteAt() {
        return bannerDeleteAt;
    }

    public void setBannerDeleteAt(Instant bannerDeleteAt) {
        this.bannerDeleteAt = bannerDeleteAt;
    }

    public TblProducer getProducer() {
        return producer;
    }

    public void setProducer(TblProducer producer) {
        this.producer = producer;
    }

    public Instant getBannerAcceptAt() {
        return bannerAcceptAt;
    }

    public void setBannerAcceptAt(Instant bannerAcceptAt) {
        this.bannerAcceptAt = bannerAcceptAt;
    }

    public TblUser getApprover() {
        return approver;
    }

    public void setApprover(TblUser approver) {
        this.approver = approver;
    }

}