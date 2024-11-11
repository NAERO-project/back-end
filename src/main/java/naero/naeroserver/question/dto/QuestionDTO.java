package naero.naeroserver.question.dto;

import jakarta.persistence.*;

import java.time.LocalDateTime;

public class QuestionDTO {

    private int questionId;
    private String questionTitle;
    private String questionContent;
    private LocalDateTime questionDate;
    private LocalDateTime questionUpdate;
    private Boolean questionStatus;
    private String questionImage;

    private int userId;

    public QuestionDTO() {
    }

    public QuestionDTO(int questionId, String questionTitle, String questionContent, LocalDateTime questionDate, LocalDateTime questionUpdate, Boolean questionStatus, String questionImage, int userId) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.questionContent = questionContent;
        this.questionDate = questionDate;
        this.questionUpdate = questionUpdate;
        this.questionStatus = questionStatus;
        this.questionImage = questionImage;
        this.userId = userId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
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

    public LocalDateTime getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(LocalDateTime questionDate) {
        this.questionDate = questionDate;
    }

    public LocalDateTime getQuestionUpdate() {
        return questionUpdate;
    }

    public void setQuestionUpdate(LocalDateTime questionUpdate) {
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "questionId=" + questionId +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionContent='" + questionContent + '\'' +
                ", questionDate=" + questionDate +
                ", questionUpdate=" + questionUpdate +
                ", questionStatus=" + questionStatus +
                ", questionImage='" + questionImage + '\'' +
                ", userId=" + userId +
                '}';
    }
}