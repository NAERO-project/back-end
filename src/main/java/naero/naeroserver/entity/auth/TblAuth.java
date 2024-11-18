package naero.naeroserver.entity.auth;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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

    @NotNull
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public String getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus;
    }

}