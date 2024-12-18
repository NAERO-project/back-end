package naero.naeroserver.entity.ship;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tbl_ship_com")
public class TblShipCom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ship_com_id", nullable = false)
    private Integer shipComId;

    @Size(max = 50)
    @NotNull
    @Column(name = "ship_com_code", nullable = false, length = 50)
    private String shipComCode;

    @Size(max = 50)
    @NotNull
    @Column(name = "ship_com_name", nullable = false, length = 50)
    private String shipComName;

    @Size(max = 50)
    @NotNull
    @Column(name = "ship_com_contact", nullable = false, length = 50)
    private String shipComContact;

    public Integer getShipComId() {
        return shipComId;
    }

    public void setShipComId(Integer id) {
        this.shipComId = id;
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

}