package naero.naeroserver.entity.ship;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tbl_shipping")
public class TblShipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipping_id", nullable = false)
    private Integer shippingId;

    @Size(max = 50)
    @Column(name = "tracking_number", length = 50)
    private String trackingNumber;

    @Size(max = 50)
    @NotNull
    @Column(name = "shipping_status", nullable = false, length = 50)
    private String shippingStatus;

    @NotNull
    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    @NotNull
    @Column(name = "ship_com_id")
    private Integer shipComId;

    public Integer getShippingId() {
        return shippingId;
    }

    public void setShippingId(Integer id) {
        this.shippingId = id;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getShipComId() {
        return shipComId;
    }

    public void setShipComId(Integer shipComId) {
        this.shipComId = shipComId;
    }

}