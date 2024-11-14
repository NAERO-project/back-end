package naero.naeroserver.inquiry.dto;

import java.time.LocalDateTime;

public class InquiryDTO {

    private Integer InquiryId;
    private String InquiryTitle;
    private String InquiryContent;
    private LocalDateTime InquiryDate;
    private LocalDateTime InquiryUpdate;
    private Boolean inquiryLock;
    private Boolean inquiryStatus;

    private Integer userId;
    private Integer productId;

    public InquiryDTO() {
    }

    public InquiryDTO(Integer inquiryId, String inquiryTitle, String inquiryContent, LocalDateTime inquiryDate, LocalDateTime inquiryUpdate, Boolean inquiryLock, Boolean inquiryStatus, Integer userId, Integer productId) {
        InquiryId = inquiryId;
        InquiryTitle = inquiryTitle;
        InquiryContent = inquiryContent;
        InquiryDate = inquiryDate;
        InquiryUpdate = inquiryUpdate;
        this.inquiryLock = inquiryLock;
        this.inquiryStatus = inquiryStatus;
        this.userId = userId;
        this.productId = productId;
    }

    public Integer getInquiryId() {
        return InquiryId;
    }

    public void setInquiryId(Integer inquiryId) {
        InquiryId = inquiryId;
    }

    public String getInquiryTitle() {
        return InquiryTitle;
    }

    public void setInquiryTitle(String inquiryTitle) {
        InquiryTitle = inquiryTitle;
    }

    public String getInquiryContent() {
        return InquiryContent;
    }

    public void setInquiryContent(String inquiryContent) {
        InquiryContent = inquiryContent;
    }

    public LocalDateTime getInquiryDate() {
        return InquiryDate;
    }

    public void setInquiryDate(LocalDateTime inquiryDate) {
        InquiryDate = inquiryDate;
    }

    public LocalDateTime getInquiryUpdate() {
        return InquiryUpdate;
    }

    public void setInquiryUpdate(LocalDateTime inquiryUpdate) {
        InquiryUpdate = inquiryUpdate;
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
                "InquiryId=" + InquiryId +
                ", InquiryTitle='" + InquiryTitle + '\'' +
                ", InquiryContent='" + InquiryContent + '\'' +
                ", InquiryDate=" + InquiryDate +
                ", InquiryUpdate=" + InquiryUpdate +
                ", inquiryLock=" + inquiryLock +
                ", inquiryStatus=" + inquiryStatus +
                ", userId=" + userId +
                ", productId=" + productId +
                '}';
    }
}
