package naero.naeroserver.member.dto;

import java.util.Date;

public class UserDTO {
    private int userId;
    private String userFullName;
    private String username;
    private String password;
    private String userEmail;
    private String userPhone;
    private String userPoint;
    private Date enrollDate;
    private char withStatus;
    private UserGradeDTO grade;

    public UserDTO() {
    }


    public UserDTO(int userId, String userFullName, String username, String password, String userEmail, String userPhone, String userPoint, Date enrollDate, char withStatus, UserGradeDTO grade) {
        this.userId = userId;
        this.userFullName = userFullName;
        this.username = username;
        this.password = password;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userPoint = userPoint;
        this.enrollDate = enrollDate;
        this.withStatus = withStatus;
        this.grade = grade;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(String userPoint) {
        this.userPoint = userPoint;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public char getWithStatus() {
        return withStatus;
    }

    public void setWithStatus(char withStatus) {
        this.withStatus = withStatus;
    }

    public UserGradeDTO getGrade() {
        return grade;
    }

    public void setGrade(UserGradeDTO grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", userFullName='" + userFullName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userPoint='" + userPoint + '\'' +
                ", enrollDate=" + enrollDate +
                ", withStatus=" + withStatus +
                ", grade=" + grade +
                '}';
    }
}
