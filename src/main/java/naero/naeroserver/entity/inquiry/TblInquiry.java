package naero.naeroserver.entity.inquiry;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import naero.naeroserver.entity.user.TblUser;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_inquiry")
public class TblInquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "inquiry_title", nullable = false, length = 50)
    private String inquiryTitle;

    @NotNull
    @Lob
    @Column(name = "inquiry_content", nullable = false)
    private String inquiryContent;

    @NotNull
    @Column(name = "inquiry_date", nullable = false)
    private Instant inquiryDate;

    @Column(name = "inquiry_update")
    private Instant inquiryUpdate;

    @NotNull
    @Column(name = "inquiry_lock", nullable = false)
    private Boolean inquiryLock = false;

    @NotNull
    @Column(name = "inquiry_status", nullable = false)
    private Boolean inquiryStatus = false;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private TblUser user;

    @OneToMany(mappedBy = "inquiry")
    private Set<TblResponse> tblResponses = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public TblUser getUser() {
        return user;
    }

    public void setUser(TblUser user) {
        this.user = user;
    }

    public Set<TblResponse> getTblResponses() {
        return tblResponses;
    }

    public void setTblResponses(Set<TblResponse> tblResponses) {
        this.tblResponses = tblResponses;
    }

}