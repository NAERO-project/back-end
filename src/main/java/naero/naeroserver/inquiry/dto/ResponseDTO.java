package naero.naeroserver.inquiry.dto;

import java.time.LocalDateTime;

public class ResponseDTO {

    private Integer responseId;
    private String responseTitle;
    private String responseContent;
    private LocalDateTime responseDate;
    private LocalDateTime responseUpdate;

    private Integer inquiryId;
    private Integer producerId;

    public ResponseDTO() {
    }

    public ResponseDTO(Integer responseId, String responseTitle, String responseContent, LocalDateTime responseDate, LocalDateTime responseUpdate, Integer inquiryId, Integer producerId) {
        this.responseId = responseId;
        this.responseTitle = responseTitle;
        this.responseContent = responseContent;
        this.responseDate = responseDate;
        this.responseUpdate = responseUpdate;
        this.inquiryId = inquiryId;
        this.producerId = producerId;
    }

    public Integer getResponseId() {
        return responseId;
    }

    public void setResponseId(Integer responseId) {
        this.responseId = responseId;
    }

    public String getResponseTitle() {
        return responseTitle;
    }

    public void setResponseTitle(String responseTitle) {
        this.responseTitle = responseTitle;
    }

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }

    public LocalDateTime getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(LocalDateTime responseDate) {
        this.responseDate = responseDate;
    }

    public LocalDateTime getResponseUpdate() {
        return responseUpdate;
    }

    public void setResponseUpdate(LocalDateTime responseUpdate) {
        this.responseUpdate = responseUpdate;
    }

    public Integer getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(Integer inquiryId) {
        this.inquiryId = inquiryId;
    }

    public Integer getProducerId() {
        return producerId;
    }

    public void setProducerId(Integer producerId) {
        this.producerId = producerId;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "responseId=" + responseId +
                ", responseTitle='" + responseTitle + '\'' +
                ", responseContent='" + responseContent + '\'' +
                ", responseDate=" + responseDate +
                ", responseUpdate=" + responseUpdate +
                ", inquiryId=" + inquiryId +
                ", producerId=" + producerId +
                '}';
    }
}
