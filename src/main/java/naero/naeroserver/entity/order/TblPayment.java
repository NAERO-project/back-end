package naero.naeroserver.entity.order;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

@Entity
@Table(name = "tbl_payment")
public class TblPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Size(max = 20)
    @NotNull
    @Column(name = "currency", nullable = false, length = 20)
    private String currency;

    @Size(max = 20)
    @NotNull
    @Column(name = "payment_method", nullable = false, length = 20)
    private String paymentMethod;

    @Size(max = 20)
    @NotNull
    @Column(name = "payment_status", nullable = false, length = 20)
    private String paymentStatus;

    @Size(max = 20)
    @NotNull
    @Column(name = "imp_uid", nullable = false, length = 20)
    private String impUid;

    @Size(max = 50)
    @NotNull
    @Column(name = "merchant_uid", nullable = false, length = 50)
    private String merchantUid;

    @Size(max = 50)
    @NotNull
    @Column(name = "transaction_id", nullable = false, length = 50)
    private String transactionId;

    @Lob
    @Column(name = "fail_reason")
    private String failReason;

    @Size(max = 255)
    @Column(name = "receipt_url")
    private String receiptUrl;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private TblOrder order;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
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

    public String getImpUid() {
        return impUid;
    }

    public void setImpUid(String impUid) {
        this.impUid = impUid;
    }

    public String getMerchantUid() {
        return merchantUid;
    }

    public void setMerchantUid(String merchantUid) {
        this.merchantUid = merchantUid;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getReceiptUrl() {
        return receiptUrl;
    }

    public void setReceiptUrl(String receiptUrl) {
        this.receiptUrl = receiptUrl;
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

    public TblOrder getOrder() {
        return order;
    }

    public void setOrder(TblOrder order) {
        this.order = order;
    }

}