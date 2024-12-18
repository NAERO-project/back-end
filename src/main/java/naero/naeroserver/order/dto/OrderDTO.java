package naero.naeroserver.order.dto;

import java.time.LocalDateTime;

public class OrderDTO {

    private Integer orderId;
    private LocalDateTime orderDatetime;
    private Integer orderTotalAmount;
    private Integer orderTotalCount;
    private String deliveryStatus;
    private String orderStatus;
    private Integer deliveryFee;
    private Integer pointDiscount;
    private Integer couponId;
    private Integer couponDiscount;
    private String recipientName;
    private String recipientPhoneNumber;
    private String postalCode;
    private String addressRoad;
    private String addressDetail;
    private String addressName;
    private String deliveryNote;
    private String trackingNumber;
    private String createdAt;
    private String updatedAt;
    private Integer userId;

    public OrderDTO() {
    }

    public OrderDTO(Integer orderId, LocalDateTime orderDatetime, Integer orderTotalAmount, Integer orderTotalCount, String deliveryStatus, String orderStatus, Integer deliveryFee, Integer pointDiscount, Integer couponId, Integer couponDiscount, String recipientName, String recipientPhoneNumber, String postalCode, String addressRoad, String addressDetail, String addressName, String deliveryNote, String trackingNumber, String createdAt, String updatedAt, Integer userId) {
        this.orderId = orderId;
        this.orderDatetime = orderDatetime;
        this.orderTotalAmount = orderTotalAmount;
        this.orderTotalCount = orderTotalCount;
        this.deliveryStatus = deliveryStatus;
        this.orderStatus = orderStatus;
        this.deliveryFee = deliveryFee;
        this.pointDiscount = pointDiscount;
        this.couponId = couponId;
        this.couponDiscount = couponDiscount;
        this.recipientName = recipientName;
        this.recipientPhoneNumber = recipientPhoneNumber;
        this.postalCode = postalCode;
        this.addressRoad = addressRoad;
        this.addressDetail = addressDetail;
        this.addressName = addressName;
        this.deliveryNote = deliveryNote;
        this.trackingNumber = trackingNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderDatetime() {
        return orderDatetime;
    }

    public void setOrderDatetime(LocalDateTime orderDatetime) {
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

    public void setPointDiscount(Integer poIntegerDiscount) {
        this.pointDiscount = poIntegerDiscount;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId=" + orderId +
                ", orderDatetime=" + orderDatetime +
                ", orderTotalAmount=" + orderTotalAmount +
                ", orderTotalCount=" + orderTotalCount +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", deliveryFee=" + deliveryFee +
                ", pointDiscount=" + pointDiscount +
                ", couponId=" + couponId +
                ", couponDiscount=" + couponDiscount +
                ", recipientName='" + recipientName + '\'' +
                ", recipientPhoneNumber='" + recipientPhoneNumber + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", addressRoad='" + addressRoad + '\'' +
                ", addressDetail='" + addressDetail + '\'' +
                ", addressName='" + addressName + '\'' +
                ", deliveryNote='" + deliveryNote + '\'' +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", userId=" + userId +
                '}';
    }
}
