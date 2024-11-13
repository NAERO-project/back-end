package naero.naeroserver.entity.inquiry;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import naero.naeroserver.entity.user.TblUser;

import java.time.Instant;

@Entity
@Table(name = "tbl_answer")
public class TblAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id", nullable = false)
    private Integer answerId;

    @Size(max = 50)
    @NotNull
    @Column(name = "answer_title", nullable = false, length = 50)
    private String answerTitle;

    @NotNull
    @Lob
    @Column(name = "answer_content", nullable = false)
    private String answerContent;

    @Column(name = "answer_date")
    private Instant answerDate;

    @Column(name = "answer_update")
    private Instant answerUpdate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    private TblQuestion question;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "answer_emp_id", nullable = false)
    private TblUser answerEmp;

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getAnswerTitle() {
        return answerTitle;
    }

    public void setAnswerTitle(String answerTitle) {
        this.answerTitle = answerTitle;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public Instant getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Instant answerDate) {
        this.answerDate = answerDate;
    }

    public Instant getAnswerUpdate() {
        return answerUpdate;
    }

    public void setAnswerUpdate(Instant answerUpdate) {
        this.answerUpdate = answerUpdate;
    }

    public TblQuestion getQuestion() {
        return question;
    }

    public void setQuestion(TblQuestion question) {
        this.question = question;
    }

    public TblUser getAnswerEmp() {
        return answerEmp;
    }

    public void setAnswerEmp(TblUser answerEmp) {
        this.answerEmp = answerEmp;
    }

}