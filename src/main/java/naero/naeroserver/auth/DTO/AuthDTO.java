package naero.naeroserver.auth.DTO;

import java.sql.Time;

public class AuthDTO {
    private int authId;
    private String authKey;
    private Time startTime;

    public AuthDTO() {
    }

    public AuthDTO(int authId, String authKey, Time startTime) {
        this.authId = authId;
        this.authKey = authKey;
        this.startTime = startTime;
    }

    public int getAuthId() {
        return authId;
    }

    public void setAuthId(int authId) {
        this.authId = authId;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }
}
