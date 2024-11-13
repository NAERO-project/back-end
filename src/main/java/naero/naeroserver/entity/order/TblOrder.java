package naero.naeroserver.entity.order;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import naero.naeroserver.entity.ship.TblShipping;
import naero.naeroserver.entity.user.TblUser;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_order")
public class TblOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "order_datetime", nullable = false)
    private Instant orderDatetime;

    @NotNull
    @Column(name = "order_total_amount", nullable = false)
    private Integer orderTotalAmount;

    @NotNull
    @Column(name = "order_total_count", nullable = false)
    private Integer orderTotalCount;

    @Size(max = 50)
    @NotNull
    @Column(name = "delivery_status", nullable = false, length = 50)
    private String deliveryStatus;

    @Size(max = 50)
    @NotNull
    @Column(name = "order_status", nullable = false, length = 50)
    private String orderStatus;

    @NotNull
    @Column(name = "delivery_fee", nullable = false)
    private Integer deliveryFee;

    @Column(name = "discount_amount")
    private Integer discountAmount;

    @Size(max = 20)
    @NotNull
    @Column(name = "recipient_name", nullable = false, length = 20)
    private String recipientName;

    @Size(max = 50)
    @NotNull
    @Column(name = "recipient_phone_number", nullable = false, length = 50)
    private String recipientPhoneNumber;

    @Size(max = 20)
    @NotNull
    @Column(name = "postal_code", nullable = false, length = 20)
    private String postalCode;

    @Size(max = 255)
    @NotNull
    @Column(name = "address_road", nullable = false)
    private String addressRoad;

    @Size(max = 255)
    @NotNull
    @Column(name = "address_detail", nullable = false)
    private String addressDetail;

    @Size(max = 255)
    @Column(name = "address_name")
    private String addressName;

    @Size(max = 255)
    @Column(name = "delivery_note")
    private String deliveryNote;

    @Size(max = 255)
    @Column(name = "tracking_number")
    private String trackingNumber;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private TblUser user;

    @OneToMany(mappedBy = "order")
    private Set<TblOrderDetail> tblOrderDetails = new LinkedHashSet<>();

    @OneToMany(mappedBy = "order")
    private Set<TblPayment> tblPayments = new LinkedHashSet<>();

//    @OneToMany(mappedBy = "order")
//    private Set<TblShipping> tblShippings = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getOrderDatetime() {
        return orderDatetime;
    }

    public void setOrderDatetime(Instant orderDatetime) {
        this.orderDatetime = orderDatetime;
    }

    public Integer getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(Integer orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    public Integer getOrderTotalCount() {
        return orderTotalCount;
    }

    public void setOrderTotalCount(Integer orderTotalCount) {
        this.orderTotalCount = orderTotalCount;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Integer deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientPhoneNumber() {
        return recipientPhoneNumber;
    }

    public void setRecipientPhoneNumber(String recipientPhoneNumber) {
        this.recipientPhoneNumber = recipientPhoneNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddressRoad() {
        return addressRoad;
    }

    public void setAddressRoad(String addressRoad) {
        this.addressRoad = addressRoad;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getDeliveryNote() {
        return deliveryNote;
    }

    public void setDeliveryNote(String deliveryNote) {
        this.deliveryNote = deliveryNote;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public TblUser getUser() {
        return user;
    }

    public void setUser(TblUser user) {
        this.user = user;
    }

    public Set<TblOrderDetail> getTblOrderDetails() {
        return tblOrderDetails;
    }

    public void setTblOrderDetails(Set<TblOrderDetail> tblOrderDetails) {
        this.tblOrderDetails = tblOrderDetails;
    }

    public Set<TblPayment> getTblPayments() {
        return tblPayments;
    }

    public void setTblPayments(Set<TblPayment> tblPayments) {
        this.tblPayments = tblPayments;
    }

//    public Set<TblShipping> getTblShippings() {
//        return tblShippings;
//    }

//    public void setTblShippings(Set<TblShipping> tblShippings) {
//        this.tblShippings = tblShippings;
//    }

}