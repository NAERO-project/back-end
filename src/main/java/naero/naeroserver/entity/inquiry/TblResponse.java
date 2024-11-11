package naero.naeroserver.entity.inquiry;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import naero.naeroserver.entity.user.TblProducer;

import java.time.Instant;

@Entity
@Table(name = "tbl_response")
public class TblResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "response_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "response_title", nullable = false, length = 50)
    private String responseTitle;

    @NotNull
    @Lob
    @Column(name = "response_content", nullable = false)
    private String responseContent;

    @NotNull
    @Column(name = "response_date", nullable = false)
    private Instant responseDate;

    @Column(name = "response_update")
    private Instant responseUpdate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inquiry_id", nullable = false)
    private TblInquiry inquiry;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "producer_id", nullable = false, referencedColumnName = "producer_id")
    private TblProducer producer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public TblInquiry getInquiry() {
        return inquiry;
    }

    public void setInquiry(TblInquiry inquiry) {
        this.inquiry = inquiry;
    }

    public TblProducer getProducer() {
        return producer;
    }

    public void setProducer(TblProducer producer) {
        this.producer = producer;
    }

}