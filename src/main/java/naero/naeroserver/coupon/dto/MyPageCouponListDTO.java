package naero.naeroserver.coupon.dto;

import java.time.Instant;

public class MyPageCouponListDTO {

    private Integer couponId;
    private String couponName;
    private Integer producerId;
    private Integer salePrice;
    private Integer maxSalePrice;
    private Integer usablePrice;
    private Instant endDate;
    private String couponType;
    private Integer couponGetId;
    private String useStatus;

    public MyPageCouponListDTO() {
    }

    public MyPageCouponListDTO(Integer couponId, String couponName, Integer producerId, Integer salePrice, Integer maxSalePrice, Integer usablePrice, Instant endDate, String couponType, Integer couponGetId, String useStatus) {
        this.couponId = couponId;
        this.couponName = couponName;
        this.producerId = producerId;
        this.salePrice = salePrice;
        this.maxSalePrice = maxSalePrice;
        this.usablePrice = usablePrice;
        this.endDate = endDate;
        this.couponType = couponType;
        this.couponGetId = couponGetId;
        this.useStatus = useStatus;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Integer getProducerId() {
        return producerId;
    }

    public void setProducerId(Integer producerId) {
        this.producerId = producerId;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getMaxSalePrice() {
        return maxSalePrice;
    }

    public void setMaxSalePrice(Integer maxSalePrice) {
        this.maxSalePrice = maxSalePrice;
    }

    public Integer getUsablePrice() {
        return usablePrice;
    }

    public void setUsablePrice(Integer usablePrice) {
        this.usablePrice = usablePrice;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public Integer getCouponGetId() {
        return couponGetId;
    }

    public void setCouponGetId(Integer couponGetId) {
        this.couponGetId = couponGetId;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    @Override
    public String toString() {
        return "MyPageCouponListDTO{" +
                "couponId=" + couponId +
                ", couponName='" + couponName + '\'' +
                ", producerId=" + producerId +
                ", salePrice=" + salePrice +
                ", maxSalePrice=" + maxSalePrice +
                ", usablePrice=" + usablePrice +
                ", endDate=" + endDate +
                ", couponType='" + couponType + '\'' +
                ", couponGetId=" + couponGetId +
                ", useStatus='" + useStatus + '\'' +
                '}';
    }
}
