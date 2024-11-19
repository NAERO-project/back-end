package naero.naeroserver.coupon.dto;

import java.time.Instant;
import java.time.LocalDateTime;

public class CouponDTO {

    private Integer couponId;
    private String couponName;
    private Integer producerId;
    private Integer salePrice;
    private Integer maxSalePrice;
    private Integer usablePrice;
    private LocalDateTime pubDate;
    private LocalDateTime endDate;
    private Integer quantity;
    private String couponType;

    public CouponDTO() {
    }

    public CouponDTO(Integer couponId, String couponName, Integer producerId, Integer salePrice, Integer maxSalePrice, Integer usablePrice, LocalDateTime pubDate, LocalDateTime endDate, Integer quantity, String couponType) {
        this.couponId = couponId;
        this.couponName = couponName;
        this.producerId = producerId;
        this.salePrice = salePrice;
        this.maxSalePrice = maxSalePrice;
        this.usablePrice = usablePrice;
        this.pubDate = pubDate;
        this.endDate = endDate;
        this.quantity = quantity;
        this.couponType = couponType;
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

    public LocalDateTime getPubDate() {
        return pubDate;
    }

    public void setPubDate(LocalDateTime pubDate) {
        this.pubDate = pubDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    @Override
    public String toString() {
        return "CouponDTO{" +
                "couponId=" + couponId +
                ", couponName='" + couponName + '\'' +
                ", producerId=" + producerId +
                ", salePrice=" + salePrice +
                ", maxSalePrice=" + maxSalePrice +
                ", usablePrice=" + usablePrice +
                ", pubDate=" + pubDate +
                ", endDate=" + endDate +
                ", quantity=" + quantity +
                ", couponType='" + couponType + '\'' +
                '}';
    }
}
