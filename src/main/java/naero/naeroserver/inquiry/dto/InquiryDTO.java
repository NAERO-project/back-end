package naero.naeroserver.inquiry.dto;

import java.time.LocalDateTime;

public class InquiryDTO {

    private Integer inquiryId;
    private String inquiryTitle;
    private String inquiryContent;
    private LocalDateTime inquiryDate;
    private LocalDateTime inquiryUpdate;
    private Boolean inquiryLock;
    private Boolean inquiryStatus;

    private Integer userId;
    private Integer productId;

    public InquiryDTO() {
    }

    public InquiryDTO(Integer inquiryId, String inquiryTitle, String inquiryContent, LocalDateTime inquiryDate, LocalDateTime inquiryUpdate, Boolean inquiryLock, Boolean inquiryStatus, Integer userId, Integer productId) {
        this.inquiryId = inquiryId;
        this.inquiryTitle = inquiryTitle;
        this.inquiryContent = inquiryContent;
        this.inquiryDate = inquiryDate;
        this.inquiryUpdate = inquiryUpdate;
        this.inquiryLock = inquiryLock;
        this.inquiryStatus = inquiryStatus;
        this.userId = userId;
        this.productId = productId;
    }

    public Integer getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(Integer inquiryId) {
        inquiryId = inquiryId;
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

    public LocalDateTime getInquiryDate() {
        return inquiryDate;
    }

    public void setInquiryDate(LocalDateTime inquiryDate) {
        this.inquiryDate = inquiryDate;
    }

    public LocalDateTime getInquiryUpdate() {
        return inquiryUpdate;
    }

    public void setInquiryUpdate(LocalDateTime inquiryUpdate) {
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

    @Override
    public String toString() {
        return "InquiryDTO{" +
                "inquiryId=" + inquiryId +
                ", inquiryTitle='" + inquiryTitle + '\'' +
                ", inquiryContent='" + inquiryContent + '\'' +
                ", inquiryDate=" + inquiryDate +
                ", inquiryUpdate=" + inquiryUpdate +
                ", inquiryLock=" + inquiryLock +
                ", inquiryStatus=" + inquiryStatus +
                ", userId=" + userId +
                ", productId=" + productId +
                '}';
    }
}
