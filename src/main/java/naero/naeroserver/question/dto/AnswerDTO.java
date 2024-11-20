package naero.naeroserver.question.dto;

import java.time.LocalDateTime;

public class AnswerDTO {

    private Integer answerId;
    private String answerTitle;
    private String answerContent;
    private LocalDateTime answerDate;
    private LocalDateTime answerUpdate;

    private Integer questionId;
    private Integer answerEmpId;

    public AnswerDTO() {
    }

    public AnswerDTO(Integer answerId, String answerTitle, String answerContent, LocalDateTime answerDate, LocalDateTime answerUpdate, Integer questionId, Integer answerEmpId) {
        this.answerId = answerId;
        this.answerTitle = answerTitle;
        this.answerContent = answerContent;
        this.answerDate = answerDate;
        this.answerUpdate = answerUpdate;

        this.questionId = questionId;
        this.answerEmpId = answerEmpId;
    }

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

    public LocalDateTime getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(LocalDateTime answerDate) {
        this.answerDate = answerDate;
    }

    public LocalDateTime getAnswerUpdate() {
        return answerUpdate;
    }

    public void setAnswerUpdate(LocalDateTime answerUpdate) {
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

    @Override
    public String toString() {
        return "AnswerDTO{" +
                "answerId=" + answerId +
                ", answerTitle='" + answerTitle + '\'' +
                ", answerContent='" + answerContent + '\'' +
                ", answerDate=" + answerDate +
                ", answerUpdate=" + answerUpdate +
                ", questionId=" + questionId +
                ", answerEmpId=" + answerEmpId +
                '}';
    }
}
