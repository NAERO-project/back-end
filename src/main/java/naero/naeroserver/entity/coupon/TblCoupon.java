package naero.naeroserver.entity.coupon;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

@Entity
@Table(name = "tbl_coupon")
public class TblCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id", nullable = false)
    private Integer couponId;

    @Size(max = 50)
    @NotNull
    @Column(name = "coupon_name", nullable = false, length = 50)
    private String couponName;

    @NotNull
    @Column(name = "producer_id", nullable = false)
    private Integer producerId;

    @Column(name = "sale_price")
    private Integer salePrice;

    @Column(name = "max_sale_price")
    private Integer maxSalePrice;

    @Column(name = "usable_price")
    private Integer usablePrice;

    @Column(name = "pub_date")
    private Instant pubDate;

    @Column(name = "end_date")
    private Instant endDate;

    @Column(name = "quantity")
    private Integer quantity;

    @Size(max = 10)
    @NotNull
    @Column(name = "coupon_type", nullable = false, length = 10)
    private String couponType;

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer id) {
        this.couponId = id;
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

    public Instant getPubDate() {
        return pubDate;
    }

    public void setPubDate(Instant pubDate) {
        this.pubDate = pubDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
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

}