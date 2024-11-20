package naero.naeroserver.entity.auth;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "tbl_auth")
public class TblAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_id", nullable = false)
    private Integer authId;

    @Size(max = 20)
    @NotNull
    @Column(name = "auth_key", nullable = false, length = 20)
    private String authKey;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "email")
    private String email;

    @Size(max = 1)
    @NotNull
    @Column(name = "auth_status", nullable = false, length = 1)
    private String authStatus;

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer id) {
        this.authId = id;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}