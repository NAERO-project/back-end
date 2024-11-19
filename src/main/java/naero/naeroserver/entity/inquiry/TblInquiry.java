package naero.naeroserver.entity.inquiry;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "tbl_inquiry")
public class TblInquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_id", nullable = false)
    private Integer inquiryId;

    @Size(max = 50)
    @NotNull
    @Column(name = "inquiry_title", nullable = false, length = 50)
    private String inquiryTitle;

    @NotNull
    @Lob
    @Column(name = "inquiry_content", nullable = false)
    private String inquiryContent;

    @Column(name = "inquiry_date")
    private Instant inquiryDate;

    @Column(name = "inquiry_update")
    private Instant inquiryUpdate;

    @NotNull
    @Column(name = "inquiry_lock", nullable = false)
    private Boolean inquiryLock = false;

    @ColumnDefault("0")
    @Column(name = "inquiry_status")
    private Boolean inquiryStatus;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @NotNull
    @Column(name = "product_id", nullable = false)
    private Integer productId;

    public Integer getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(Integer getInquiryId) {
        this.inquiryId = getInquiryId;
    }

    public String getInquiryTitle() {
        return inquiryTitle;
    }

    public void setInquiryTitle(String inquiryTitle) {
        this.inquiryTitle = inquiryTitle;
    }

    public String getInquiryContent() {
        return inquiryContent;
    }

    public void setInquiryContent(String inquiryContent) {
        this.inquiryContent = inquiryContent;
    }

    public Instant getInquiryDate() {
        return inquiryDate;
    }

    public void setInquiryDate(Instant inquiryDate) {
        this.inquiryDate = inquiryDate;
    }

    public Instant getInquiryUpdate() {
        return inquiryUpdate;
    }

    public void setInquiryUpdate(Instant inquiryUpdate) {
        this.inquiryUpdate = inquiryUpdate;
    }

    public Boolean getInquiryLock() {
        return inquiryLock;
    }

    public void setInquiryLock(Boolean inquiryLock) {
        this.inquiryLock = inquiryLock;
    }

    public Boolean getInquiryStatus() {
        return inquiryStatus;
    }

    public void setInquiryStatus(Boolean inquiryStatus) {
        this.inquiryStatus = inquiryStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

}