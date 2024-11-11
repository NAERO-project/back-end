package naero.naeroserver.entity.coupon;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import naero.naeroserver.entity.user.TblUser;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "tbl_coupon_list")
public class TblCouponList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`Key`", nullable = false)
    private Integer id;

    @Size(max = 1)
    @ColumnDefault("'N'")
    @Column(name = "use_status", length = 1)
    private String useStatus;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private TblUser user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "coupon_id", nullable = false)
    private TblCoupon coupon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public TblUser getUser() {
        return user;
    }

    public void setUser(TblUser user) {
        this.user = user;
    }

    public TblCoupon getCoupon() {
        return coupon;
    }

    public void setCoupon(TblCoupon coupon) {
        this.coupon = coupon;
    }

}