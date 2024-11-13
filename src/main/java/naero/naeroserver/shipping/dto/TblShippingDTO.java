package naero.naeroserver.shipping.dto;

public class TblShippingDTO {

    private int shippingId;
    private String trackingNumber;
    private String shippingStatus;
    //    OrderDTO order;
    /* 일단, OrderDTO 없이 dummy 데이터로 진행, 즉 orderId에 연결이 없는 것을 가정 */
    private int orderId;
    private TblShipComDTO shipCom;

    public TblShippingDTO() {
    }

    public TblShippingDTO(int shippingId, String trackingNumber,
                          String shippingStatus, int orderId, TblShipComDTO shipCom) {
        this.shippingId = shippingId;
        this.trackingNumber = trackingNumber;
        this.shippingStatus = shippingStatus;
        this.orderId = orderId;
        this.shipCom = shipCom;
    }

    public int getShippingId() {
        return shippingId;
    }

    public void setShippingId(int shippingId) {
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public TblShipComDTO getShipCom() {
        return shipCom;
    }

    public void setShipCom(TblShipComDTO shipCom) {
        this.shipCom = shipCom;
    }

    @Override
    public String toString() {
        return "TblShippingDTO{" +
                "shippingId=" + shippingId +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", shippingStatus='" + shippingStatus + '\'' +
                ", orderId=" + orderId +
                ", shipCom=" + shipCom +
                '}';
    }
}
