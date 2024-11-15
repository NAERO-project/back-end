package naero.naeroserver.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "tbl_banner")
public class TblBanner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banner_id", nullable = false)
    private Integer bannerId;

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
    @Column(name = "producer_id", nullable = false)
    private Integer producerId;

    @Size(max = 1)
    @ColumnDefault("'N'")
    @Column(name = "banner_accept_status", length = 1)
    private String bannerAcceptStatus;

    @Column(name = "banner_accept_at")
    private Instant bannerAcceptAt;

    @Column(name = "approver_id")
    private Integer approverId;

    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer id) {
        this.bannerId = id;
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

    public Integer getProducerId() {
        return producerId;
    }

    public void setProducerId(Integer producerId) {
        this.producerId = producerId;
    }

    public String getBannerAcceptStatus() {
        return bannerAcceptStatus;
    }

    public void setBannerAcceptStatus(String bannerAcceptStatus) {
        this.bannerAcceptStatus = bannerAcceptStatus;
    }

    public Instant getBannerAcceptAt() {
        return bannerAcceptAt;
    }

    public void setBannerAcceptAt(Instant bannerAcceptAt) {
        this.bannerAcceptAt = bannerAcceptAt;
    }

    public Integer getApproverId() {
        return approverId;
    }

    public void setApproverId(Integer approverId) {
        this.approverId = approverId;
    }

}