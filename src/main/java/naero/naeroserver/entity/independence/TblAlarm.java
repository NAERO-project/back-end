package naero.naeroserver.entity.independence;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import naero.naeroserver.entity.user.TblUser;

import java.time.Instant;

@Entity
@Table(name = "tbl_alarm")
public class TblAlarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "alarm_url")
    private String alarmUrl;

    @Size(max = 255)
    @Column(name = "alarm_detail")
    private String alarmDetail;

    @Column(name = "check_status")
    private Boolean checkStatus;

    @Column(name = "alarm_send_date")
    private Instant alarmSendDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private TblUser user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlarmUrl() {
        return alarmUrl;
    }

    public void setAlarmUrl(String alarmUrl) {
        this.alarmUrl = alarmUrl;
    }

    public String getAlarmDetail() {
        return alarmDetail;
    }

    public void setAlarmDetail(String alarmDetail) {
        this.alarmDetail = alarmDetail;
    }

    public Boolean getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Boolean checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Instant getAlarmSendDate() {
        return alarmSendDate;
    }

    public void setAlarmSendDate(Instant alarmSendDate) {
        this.alarmSendDate = alarmSendDate;
    }

    public TblUser getUser() {
        return user;
    }

    public void setUser(TblUser user) {
        this.user = user;
    }

}