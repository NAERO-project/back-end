package naero.naeroserver.entity.inquiry;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
    @Column(name = "question_id", nullable = false)
    private Integer questionId;

    @NotNull
    @Column(name = "answer_emp_id", nullable = false)
    private Integer answerEmpId;

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer id) {
        this.answerId = id;
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

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getAnswerEmpId() {
        return answerEmpId;
    }

    public void setAnswerEmpId(Integer answerEmpId) {
        this.answerEmpId = answerEmpId;
    }

}