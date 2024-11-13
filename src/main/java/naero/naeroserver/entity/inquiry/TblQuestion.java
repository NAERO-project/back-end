package naero.naeroserver.entity.inquiry;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import naero.naeroserver.entity.user.TblUser;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_question")
public class TblQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", nullable = false)
    private Integer questionId;

    @Size(max = 50)
    @NotNull
    @Column(name = "question_title", nullable = false, length = 50)
    private String questionTitle;

    @NotNull
    @Lob
    @Column(name = "question_content", nullable = false)
    private String questionContent;

    @Column(name = "question_date")
    private Instant questionDate;

    @Column(name = "question_update")
    private Instant questionUpdate;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "question_status", nullable = false)
    private Boolean questionStatus = false;

    @Size(max = 255)
    @Column(name = "question_image")
    private String questionImage;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private TblUser user;

    @OneToMany(mappedBy = "question")
    private Set<TblAnswer> tblAnswers = new LinkedHashSet<>();

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public Instant getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(Instant questionDate) {
        this.questionDate = questionDate;
    }

    public Instant getQuestionUpdate() {
        return questionUpdate;
    }

    public void setQuestionUpdate(Instant questionUpdate) {
        this.questionUpdate = questionUpdate;
    }

    public Boolean getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(Boolean questionStatus) {
        this.questionStatus = questionStatus;
    }

    public String getQuestionImage() {
        return questionImage;
    }

    public void setQuestionImage(String questionImage) {
        this.questionImage = questionImage;
    }

    public TblUser getUser() {
        return user;
    }

    public void setUser(TblUser user) {
        this.user = user;
    }

    public Set<TblAnswer> getTblAnswers() {
        return tblAnswers;
    }

    public void setTblAnswers(Set<TblAnswer> tblAnswers) {
        this.tblAnswers = tblAnswers;
    }

}