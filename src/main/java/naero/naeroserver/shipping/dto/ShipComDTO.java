package naero.naeroserver.shipping.dto;

public class ShipComDTO {
    private Integer shipComId;
    private String shipComCode;
    private String shipComName;
    private String shipComContact;

    public ShipComDTO() {
    }

    public ShipComDTO(Integer shipComId, String shipComCode,
                      String shipComName, String shipComContact) {
        this.shipComId = shipComId;
        this.shipComCode = shipComCode;
        this.shipComName = shipComName;
        this.shipComContact = shipComContact;
    }

    public Integer getShipComId() {
        return shipComId;
    }

    public void setShipComId(Integer shipComId) {
        this.shipComId = shipComId;
    }

    public String getShipComCode() {
        return shipComCode;
    }

    public void setShipComCode(String shipComCode) {
        this.shipComCode = shipComCode;
    }

    public String getShipComName() {
        return shipComName;
    }

    public void setShipComName(String shipComName) {
        this.shipComName = shipComName;
    }

    public String getShipComContact() {
        return shipComContact;
    }

    public void setShipComContact(String shipComContact) {
        this.shipComContact = shipComContact;
    }

    @Override
    public String toString() {
        return "ShipComDTO{" +
                "shipComId=" + shipComId +
                ", shipComCode='" + shipComCode + '\'' +
                ", shipComName='" + shipComName + '\'' +
                ", shipComContact='" + shipComContact + '\'' +
                '}';
    }
}
