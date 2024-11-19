package naero.naeroserver.order.dto;

import java.time.LocalDateTime;

public class OrderDetailDTO {

    int orderDetailId;
    int optionId;
    int count;
    int amount;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    int shippingId;
    int orderId;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int orderDetailId, int productId, int count, int amount, LocalDateTime createdAt, LocalDateTime updatedAt, int shippingId, int orderId) {
        this.orderDetailId = orderDetailId;
        this.optionId = productId;
        this.count = count;
        this.amount = amount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.shippingId = shippingId;
        this.orderId = orderId;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public int getShippingId() {
        return shippingId;
    }

    public void setShippingId(int shippingId) {
        this.shippingId = shippingId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "orderDetailId=" + orderDetailId +
                ", productId=" + optionId +
                ", count=" + count +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", shippingId=" + shippingId +
                ", orderId=" + orderId +
                '}';
    }
}
