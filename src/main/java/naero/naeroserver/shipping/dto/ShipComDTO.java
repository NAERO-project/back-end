package naero.naeroserver.shipping.dto;

public class ShipComDTO {
    private int shippingId;
    private String shippingComCode;
    private String shippingComName;
    private String shippingComContact;

    public ShipComDTO() {
    }

    public ShipComDTO(int shippingId, String shippingComCode, String shippingComName, String shippingComContact) {
        this.shippingId = shippingId;
        this.shippingComCode = shippingComCode;
        this.shippingComName = shippingComName;
        this.shippingComContact = shippingComContact;
    }

    public int getShippingId() {
        return shippingId;
    }

    public void setShippingId(int shippingId) {
        this.shippingId = shippingId;
    }

    public String getShippingComCode() {
        return shippingComCode;
    }

    public void setShippingComCode(String shippingComCode) {
        this.shippingComCode = shippingComCode;
    }

    public String getShippingComName() {
        return shippingComName;
    }

    public void setShippingComName(String shippingComName) {
        this.shippingComName = shippingComName;
    }

    public String getShippingComContact() {
        return shippingComContact;
    }

    public void setShippingComContact(String shippingComContact) {
        this.shippingComContact = shippingComContact;
    }

    @Override
    public String toString() {
        return "TblShipCom{" +
                "shippingId=" + shippingId +
                ", shippingComCode='" + shippingComCode + '\'' +
                ", shippingComName='" + shippingComName + '\'' +
                ", shippingComContact='" + shippingComContact + '\'' +
                '}';
    }
}
