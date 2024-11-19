package naero.naeroserver.shipping.dto;

public class ShippingDTO {

    private int shippingId;
    private String trackingNumber;
    private String shippingStatus;
    private int orderId;
    private int shipComId;

    public ShippingDTO() {
    }

    public ShippingDTO(int shippingId, String trackingNumber,
                       String shippingStatus, int orderId, int shipComId) {
        this.shippingId = shippingId;
        this.trackingNumber = trackingNumber;
        this.shippingStatus = shippingStatus;
        this.orderId = orderId;
        this.shipComId = shipComId;
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

    public int getShipComId() {
        return shipComId;
    }

    public void setShipComId(int shipComId) {
        this.shipComId = shipComId;
    }

    @Override
    public String toString() {
        return "TblShippingDTO{" +
                "shippingId=" + shippingId +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", shippingStatus='" + shippingStatus + '\'' +
                ", orderId=" + orderId +
                ", shipComId=" + shipComId +
                '}';
    }
}
