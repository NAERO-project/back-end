package naero.naeroserver.auth.DTO;

import java.sql.Time;

public class AuthDTO {
    private Integer authId;
    private String authKey;
    private String email;
    private Time endTime;
    private char authStatus;

    public AuthDTO() {
    }

    public AuthDTO(Integer authId, String authKey, String email, Time startTime, char authStatus) {
        this.authId = authId;
        this.authKey = authKey;
        this.email = email;
        this.endTime = startTime;
        this.authStatus = authStatus;
    }

    public char getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(char authStatus) {
        this.authStatus = authStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
}
