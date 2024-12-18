//package naero.naeroserver.order.entity;
//
//import java.time.LocalDateTime;
//
//public class OrderDetail {
//
//    int orderDetailId;
//    int productId;
//    int count;
//    int amount;
//    LocalDateTime createdAt;
//    LocalDateTime updatedAt;
//    int shippingId;
//    int orderId;
//
//    public OrderDetail() {
//    }
//
//    public OrderDetail(int orderDetailId, int productId, int count, int amount, LocalDateTime createdAt, LocalDateTime updatedAt, int shippingId, int orderId) {
//        this.orderDetailId = orderDetailId;
//        this.productId = productId;
//        this.count = count;
//        this.amount = amount;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//        this.shippingId = shippingId;
//        this.orderId = orderId;
//    }
//
//    public int getOrderDetailId() {
//        return orderDetailId;
//    }
//
//    public void setOrderDetailId(int orderDetailId) {
//        this.orderDetailId = orderDetailId;
//    }
//
//    public int getProductId() {
//        return productId;
//    }
//
//    public void setProductId(int productId) {
//        this.productId = productId;
//    }
//
//    public int getCount() {
//        return count;
//    }
//
//    public void setCount(int count) {
//        this.count = count;
//    }
//
//    public int getAmount() {
//        return amount;
//    }
//
//    public void setAmount(int amount) {
//        this.amount = amount;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public LocalDateTime getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(LocalDateTime updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//    public int getShippingId() {
//        return shippingId;
//    }
//
//    public void setShippingId(int shippingId) {
//        this.shippingId = shippingId;
//    }
//
//    public int getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(int orderId) {
//        this.orderId = orderId;
//    }
//
//    @Override
//    public String toString() {
//        return "OrderDetailDTO{" +
//                "orderDetailId=" + orderDetailId +
//                ", productId=" + productId +
//                ", count=" + count +
//                ", amount=" + amount +
//                ", createdAt=" + createdAt +
//                ", updatedAt=" + updatedAt +
//                ", shippingId=" + shippingId +
//                ", orderId=" + orderId +
//                '}';
//    }
//}
