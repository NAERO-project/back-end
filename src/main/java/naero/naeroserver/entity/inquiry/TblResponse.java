
package naero.naeroserver.entity.inquiry;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

@Entity
@Table(name = "tbl_response")
public class TblResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "response_id", nullable = false)
    private Integer responseId;

    @Size(max = 50)
    @NotNull
    @Column(name = "response_title", nullable = false, length = 50)
    private String responseTitle;

    @NotNull
    @Lob
    @Column(name = "response_content", nullable = false)
    private String responseContent;

    @Column(name = "response_date")
    private Instant responseDate;

    @Column(name = "response_update")
    private Instant responseUpdate;

    @NotNull
    @Column(name = "inquiry_id", nullable = false)
    private Integer inquiryId;

    @NotNull
    @Column(name = "producer_id", nullable = false)
    private Integer producerId;

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

    public Instant getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Instant responseDate) {
        this.responseDate = responseDate;
    }

    public Instant getResponseUpdate() {
        return responseUpdate;
    }

    public void setResponseUpdate(Instant responseUpdate) {
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

}
