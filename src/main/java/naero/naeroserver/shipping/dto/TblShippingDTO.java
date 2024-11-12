package naero.naeroserver.shipping.dto;

public class TblShippingDTO {

    int shippingId;
    String trackingNumber;
    String shippingStatus;
    OrderDTO order;
    TblShipComDTO shipCom;

    public TblShippingDTO() {
    }

    public TblShippingDTO(int shippingId, String trackingNumber,
                          String shippingStatus, OrderDTO order, TblShipComDTO shipCom) {
        this.shippingId = shippingId;
        this.trackingNumber = trackingNumber;
        this.shippingStatus = shippingStatus;
        this.order = order;
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

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
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
                ", order=" + order +
                ", shipCom=" + shipCom +
                '}';
    }
}
