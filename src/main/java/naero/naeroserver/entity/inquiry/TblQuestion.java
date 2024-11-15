package naero.naeroserver.entity.inquiry;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

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

    @ColumnDefault("0")
    @Column(name = "question_status")
    private Boolean questionStatus;

    @Size(max = 255)
    @Column(name = "question_image")
    private String questionImage;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer id) {
        this.questionId = id;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}