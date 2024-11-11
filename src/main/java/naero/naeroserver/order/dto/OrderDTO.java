package naero.naeroserver.order.dto;

import java.time.LocalDateTime;

public class OrderDTO {

    private int orderId;
    private LocalDateTime orderDatetime;
    private int orderTotalAmount;
    private int orderTotalCount;
    private String deliveryStatus;
    private String orderStatus;
    private int deliveryFee;
    private int discountAmount;
    private String recipientName;
    private String recipientPhoneNumber;
    private String postalCode;
    private String addressRoad;
    private String addressDetail;
    private String addressName;
    private String deliveryNote;
    private String trackingNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int userId;

    public OrderDTO() {
    }

    public OrderDTO(int orderId, LocalDateTime orderDatetime, int orderTotalAmount, int orderTotalCount, String deliveryStatus, String orderStatus, int deliveryFee, int discountAmount, String recipientName, String recipientPhoneNumber, String postalCode, String addressRoad, String addressDetail, String addressName, String deliveryNote, String trackingNumber, LocalDateTime createdAt, LocalDateTime updatedAt, int userId) {
        this.orderId = orderId;
        this.orderDatetime = orderDatetime;
        this.orderTotalAmount = orderTotalAmount;
        this.orderTotalCount = orderTotalCount;
        this.deliveryStatus = deliveryStatus;
        this.orderStatus = orderStatus;
        this.deliveryFee = deliveryFee;
        this.discountAmount = discountAmount;
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderDatetime() {
        return orderDatetime;
    }

    public void setOrderDatetime(LocalDateTime orderDatetime) {
        this.orderDatetime = orderDatetime;
    }

    public int getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(int orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    public int getOrderTotalCount() {
        return orderTotalCount;
    }

    public void setOrderTotalCount(int orderTotalCount) {
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

    public int getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(int deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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
                ", discountAmount=" + discountAmount +
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
