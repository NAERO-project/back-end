package naero.naeroserver.entity.coupon;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import naero.naeroserver.entity.user.TblProducer;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_coupon")
public class TblCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "coupon_name", nullable = false, length = 50)
    private String couponName;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "producer_id", nullable = false, referencedColumnName = "producer_id")
    private TblProducer producer;

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

    @OneToMany(mappedBy = "coupon")
    private Set<TblCouponList> tblCouponLists = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public TblProducer getProducer() {
        return producer;
    }

    public void setProducer(TblProducer producer) {
        this.producer = producer;
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

    public Set<TblCouponList> getTblCouponLists() {
        return tblCouponLists;
    }

    public void setTblCouponLists(Set<TblCouponList> tblCouponLists) {
        this.tblCouponLists = tblCouponLists;
    }

}