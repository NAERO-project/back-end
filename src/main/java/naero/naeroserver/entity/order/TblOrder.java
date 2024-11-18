package naero.naeroserver.entity.order;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

@Entity
@Table(name = "tbl_order")
public class TblOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    @Column(name = "order_datetime")
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

    @Column(name = "point_discount")
    private Integer pointDiscount;

    @Column(name = "coupon_discount")
    private Integer couponDiscount;

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

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer id) {
        this.orderId = id;
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

    public Integer getPointDiscount() {
        return pointDiscount;
    }

    public void setPointDiscount(Integer pointDiscount) {
        this.pointDiscount = pointDiscount;
    }

    public Integer getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(Integer couponDiscount) {
        this.couponDiscount = couponDiscount;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}