package naero.naeroserver.banner.dto;

public class BannerDTO {
    private int bannerId;
    private String bannerThumbnail;
    private String bannerImg;
    private String bannerUrl;
    private String bannerCreateAt;
    private String bannerDeleteAt;
    private int producerId;
    private String bannerAcceptStatus;
    private String bannerAcceptAt;
    private String bannerCheck;
    private Integer approverId;

    public BannerDTO() {
    }

    public BannerDTO(int bannerId, String bannerThumbnail, String bannerImg, String bannerUrl, String bannerCreateAt, String bannerDeleteAt, int producerId, String bannerAcceptStatus, String bannerAcceptAt, String bannerCheck, Integer approverId) {
        this.bannerId = bannerId;
        this.bannerThumbnail = bannerThumbnail;
        this.bannerImg = bannerImg;
        this.bannerUrl = bannerUrl;
        this.bannerCreateAt = bannerCreateAt;
        this.bannerDeleteAt = bannerDeleteAt;
        this.producerId = producerId;
        this.bannerAcceptStatus = bannerAcceptStatus;
        this.bannerAcceptAt = bannerAcceptAt;
        this.bannerCheck = bannerCheck;
        this.approverId = approverId;
    }

    public int getBannerId() {
        return bannerId;
    }

    public void setBannerId(int bannerId) {
        this.bannerId = bannerId;
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

    public String getBannerCreateAt() {
        return bannerCreateAt;
    }

    public void setBannerCreateAt(String bannerCreateAt) {
        this.bannerCreateAt = bannerCreateAt;
    }

    public String getBannerDeleteAt() {
        return bannerDeleteAt;
    }

    public void setBannerDeleteAt(String bannerDeleteAt) {
        this.bannerDeleteAt = bannerDeleteAt;
    }

    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    public String getBannerAcceptStatus() {
        return bannerAcceptStatus;
    }

    public void setBannerAcceptStatus(String bannerAcceptStatus) {
        this.bannerAcceptStatus = bannerAcceptStatus;
    }

    public String getBannerAcceptAt() {
        return bannerAcceptAt;
    }

    public void setBannerAcceptAt(String bannerAcceptAt) {
        this.bannerAcceptAt = bannerAcceptAt;
    }

    public String getBannerCheck() {
        return bannerCheck;
    }

    public void setBannerCheck(String bannerCheck) {
        this.bannerCheck = bannerCheck;
    }

    public Integer getApproverId() {
        return approverId;
    }

    public void setApproverId(Integer approverId) {
        this.approverId = approverId;
    }

    @Override
    public String toString() {
        return "BannerDTO{" +
                "bannerId=" + bannerId +
                ", bannerThumbnail='" + bannerThumbnail + '\'' +
                ", bannerImg='" + bannerImg + '\'' +
                ", bannerUrl='" + bannerUrl + '\'' +
                ", bannerCreateAt='" + bannerCreateAt + '\'' +
                ", bannerDeleteAt='" + bannerDeleteAt + '\'' +
                ", producerId=" + producerId +
                ", bannerAcceptStatus='" + bannerAcceptStatus + '\'' +
                ", bannerAcceptAt='" + bannerAcceptAt + '\'' +
                ", bannerCheck='" + bannerCheck + '\'' +
                ", approverId=" + approverId +
                '}';
    }
}
