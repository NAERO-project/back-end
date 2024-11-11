package naero.naeroserver.order.dto;

import java.time.LocalDateTime;

public class PaymentDTO {

    private int paymentId;
    private int userId;
    private int amount;
    private String currency;
    private String paymentMethod;
    private String paymentStatus;
    private String imp_uid;
    private String merchant_uid;
    private String transaction_id;
    private String fail_reason;
    private String receipt_url;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int OrderId;

    public PaymentDTO() {
    }

    public PaymentDTO(int paymentId, int userId, int amount, String currency, String paymentMethod, String paymentStatus, String imp_uid, String merchant_uid, String transaction_id, String fail_reason, String receipt_url, LocalDateTime createdAt, LocalDateTime updatedAt, int orderId) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.amount = amount;
        this.currency = currency;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.imp_uid = imp_uid;
        this.merchant_uid = merchant_uid;
        this.transaction_id = transaction_id;
        this.fail_reason = fail_reason;
        this.receipt_url = receipt_url;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        OrderId = orderId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getImp_uid() {
        return imp_uid;
    }

    public void setImp_uid(String imp_uid) {
        this.imp_uid = imp_uid;
    }

    public String getMerchant_uid() {
        return merchant_uid;
    }

    public void setMerchant_uid(String merchant_uid) {
        this.merchant_uid = merchant_uid;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getFail_reason() {
        return fail_reason;
    }

    public void setFail_reason(String fail_reason) {
        this.fail_reason = fail_reason;
    }

    public String getReceipt_url() {
        return receipt_url;
    }

    public void setReceipt_url(String receipt_url) {
        this.receipt_url = receipt_url;
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

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "paymentId=" + paymentId +
                ", userId=" + userId +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", imp_uid='" + imp_uid + '\'' +
                ", merchant_uid='" + merchant_uid + '\'' +
                ", transaction_id='" + transaction_id + '\'' +
                ", fail_reason='" + fail_reason + '\'' +
                ", receipt_url='" + receipt_url + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", OrderId=" + OrderId +
                '}';
    }
}
