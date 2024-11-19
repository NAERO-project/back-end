package naero.naeroserver.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import naero.naeroserver.entity.auth.TblUserRole;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_user")
public class TblUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Size(max = 20)
    @Column(name = "user_fullname", length = 20)
    private String userFullname;

    @Size(max = 20)
    @NotNull
    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @Size(max = 255)
    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @Size(max = 255)
    @Column(name = "user_email")
    private String userEmail;

    @Size(max = 20)
    @Column(name = "user_phone", length = 20)
    private String userPhone;

    @ColumnDefault("0")
    @Column(name = "user_point")
    private Integer userPoint;

    @Column(name = "enroll_date")
    private Date enrollDate;

    @Size(max = 1)
    @ColumnDefault("'N'")
    @Column(name = "with_status", length = 1)
    private String withStatus;

    @ManyToOne
    @JoinColumn(name = "grade_id")
    private TblGrade grade;

    @OneToMany(mappedBy = "user")
    private Set<TblUserRole> tblUserRoles = new LinkedHashSet<>();


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer id) {
        this.userId = id;
    }

    public String getUserFullname() {
        return userFullname;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
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

    public Integer getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(Integer userPoint) {
        this.userPoint = userPoint;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public String getWithStatus() {
        return withStatus;
    }

    public void setWithStatus(String withStatus) {
        this.withStatus = withStatus;
    }

    public TblGrade getGrade() {
        return grade;
    }

    public void setGrade(TblGrade grade) {
        this.grade = grade;
    }

    public Set<TblUserRole> getTblUserRoles() {
        return tblUserRoles;
    }

    public void setTblUserRoles(Set<TblUserRole> tblUserRoles) {
        this.tblUserRoles = tblUserRoles;
    }

    @Override
    public String toString() {
        return "TblUser{" +
                "userId=" + userId +
                ", userFullname='" + userFullname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userPoint=" + userPoint +
                ", enrollDate=" + enrollDate +
                ", withStatus='" + withStatus + '\'' +
                ", gradeId=" + grade +
                ", tblUserRoles=" + tblUserRoles +
                '}';
    }
}