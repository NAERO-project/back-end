package naero.naeroserver.entity.ship;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import naero.naeroserver.entity.order.TblOrder;
import naero.naeroserver.entity.order.TblOrderDetail;

import java.util.LinkedHashSet;
import java.util.Set;

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

    //    @NotNull
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "order_id", nullable = false)
//    private TblOrder order;
    /* 일단, OrderDTO 없이 dummy 데이터로 진행, 즉 orderId에 FK 연결이 없는 것을 가정 */
    @Size(max = 50)
    @NotNull
    @Column(name = "order_id", nullable = false, length = 50)
    private Integer orderId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ship_com_id", nullable = false)
    private TblShipCom shipCom;

//    @OneToMany(mappedBy = "shipping")
//    private Set<TblOrderDetail> tblOrderDetails = new LinkedHashSet<>();

    public Integer getShippingId() {
        return shippingId;
    }

    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
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

    //    public TblOrder getOrder() {
//        return order;
//    }
    public Integer getOrderId(Integer orderId) {
        return this.orderId = orderId;
    }

    //    public void setOrder(TblOrder order) {
//        this.order = order;
//    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public TblShipCom getShipCom() {
        return shipCom;
    }

    public void setShipCom(TblShipCom shipCom) {
        this.shipCom = shipCom;
    }

//    public Set<TblOrderDetail> getTblOrderDetails() {
//        return tblOrderDetails;
//    }

//    public void setTblOrderDetails(Set<TblOrderDetail> tblOrderDetails) {
//        this.tblOrderDetails = tblOrderDetails;
//    }

}