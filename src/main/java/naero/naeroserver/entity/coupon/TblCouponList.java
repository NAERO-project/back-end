package naero.naeroserver.entity.coupon;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "tbl_coupon_list")
public class TblCouponList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_get_id", nullable = false)
    private Integer couponGetId;

    @Size(max = 1)
    @ColumnDefault("'N'")
    @Column(name = "use_status", length = 1)
    private String useStatus;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @NotNull
    @Column(name = "coupon_id", nullable = false)
    private Integer couponId;

    public Integer getCouponGetId() {
        return couponGetId;
    }

    public void setCouponGetId(Integer id) {
        this.couponGetId = id;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

}