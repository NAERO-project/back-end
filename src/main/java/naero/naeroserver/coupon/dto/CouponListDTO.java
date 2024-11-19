package naero.naeroserver.coupon.dto;

public class CouponListDTO {

    int couponGetId;
    String useStatus;
    int userId;
    int couponId;

    public CouponListDTO() {
    }

    public CouponListDTO(int couponGetId, String useStatus, int userId, int couponId) {
        this.couponGetId = couponGetId;
        this.useStatus = useStatus;
        this.userId = userId;
        this.couponId = couponId;
    }

    public int getCouponGetId() {
        return couponGetId;
    }

    public void setCouponGetId(int couponGetId) {
        this.couponGetId = couponGetId;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    @Override
    public String toString() {
        return "CouponListDTO{" +
                "couponGetId=" + couponGetId +
                ", useStatus='" + useStatus + '\'' +
                ", userId=" + userId +
                ", couponId=" + couponId +
                '}';
    }
}
