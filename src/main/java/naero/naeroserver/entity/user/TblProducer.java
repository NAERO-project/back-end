package naero.naeroserver.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import naero.naeroserver.entity.coupon.TblCoupon;
import naero.naeroserver.entity.inquiry.TblResponse;
import naero.naeroserver.entity.liked.TblLikedSeller;
import naero.naeroserver.entity.product.TblCategoryLarge;
import naero.naeroserver.entity.product.TblProduct;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_producer")
public class TblProducer {
    @Id
    @Column(name = "producer_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "producer_id", nullable = false)
    private TblUser producer;

    @Size(max = 20)
    @Column(name = "busi_no", length = 20)
    private String busiNo;

    @Size(max = 255)
    @Column(name = "producer_add")
    private String producerAdd;

    @Size(max = 20)
    @Column(name = "producer_name", length = 20)
    private String producerName;

    @Size(max = 20)
    @Column(name = "producer_phone", length = 20)
    private String producerPhone;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ColumnDefault("1")
    @JoinColumn(name = "pgrade_id")
    private TblProducerGrade pgrade;

    @Column(name = "delivery_fee")
    private Integer deliveryFee;

    @Column(name = "delivery_crit")
    private Integer deliveryCrit;

    @OneToMany(mappedBy = "producer")
    private Set<TblCategoryLarge.TblBanner> tblBanners = new LinkedHashSet<>();

    @OneToMany(mappedBy = "producer")
    private Set<TblCoupon> tblCoupons = new LinkedHashSet<>();

    @OneToMany(mappedBy = "producer")
    private Set<TblLikedSeller> tblLikedSellers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "producer")
    private Set<TblProduct> tblProducts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "producer")
    private Set<TblResponse> tblResponses = new LinkedHashSet<>();

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producer_id")
    private TblProducer tblProducer;

    public TblProducer getTblProducer() {
        return tblProducer;
    }

    public void setTblProducer(TblProducer tblProducer) {
        this.tblProducer = tblProducer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TblUser getProducer() {
        return producer;
    }

    public void setProducer(TblUser tblUser) {
        this.producer = tblUser;
    }

    public String getBusiNo() {
        return busiNo;
    }

    public void setBusiNo(String busiNo) {
        this.busiNo = busiNo;
    }

    public String getProducerAdd() {
        return producerAdd;
    }

    public void setProducerAdd(String producerAdd) {
        this.producerAdd = producerAdd;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public String getProducerPhone() {
        return producerPhone;
    }

    public void setProducerPhone(String producerPhone) {
        this.producerPhone = producerPhone;
    }

    public TblProducerGrade getPgrade() {
        return pgrade;
    }

    public void setPgrade(TblProducerGrade pgrade) {
        this.pgrade = pgrade;
    }

    public Integer getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Integer deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Integer getDeliveryCrit() {
        return deliveryCrit;
    }

    public void setDeliveryCrit(Integer deliveryCrit) {
        this.deliveryCrit = deliveryCrit;
    }

    public Set<TblCategoryLarge.TblBanner> getTblBanners() {
        return tblBanners;
    }

    public void setTblBanners(Set<TblCategoryLarge.TblBanner> tblBanners) {
        this.tblBanners = tblBanners;
    }

    public Set<TblCoupon> getTblCoupons() {
        return tblCoupons;
    }

    public void setTblCoupons(Set<TblCoupon> tblCoupons) {
        this.tblCoupons = tblCoupons;
    }

    public Set<TblLikedSeller> getTblLikedSellers() {
        return tblLikedSellers;
    }

    public void setTblLikedSellers(Set<TblLikedSeller> tblLikedSellers) {
        this.tblLikedSellers = tblLikedSellers;
    }

    public Set<TblProduct> getTblProducts() {
        return tblProducts;
    }

    public void setTblProducts(Set<TblProduct> tblProducts) {
        this.tblProducts = tblProducts;
    }

    public Set<TblResponse> getTblResponses() {
        return tblResponses;
    }

    public void setTblResponses(Set<TblResponse> tblResponses) {
        this.tblResponses = tblResponses;
    }

}