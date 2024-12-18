package naero.naeroserver.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "tbl_option")
public class TblOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id", nullable = false)
    private Integer optionId;

    @Size(max = 255)
    @Column(name = "option_desc")
    private String optionDesc;

    @Column(name = "add_price")
    private Integer addPrice;

    @Column(name="option_check", length = 1)
    @ColumnDefault("'Y'")
    private String optionCheck;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "option_quantity")
    private Integer optionQuantity;

    public String getOptionCheck() {
        return optionCheck;
    }

    public void setOptionCheck(String optionCheck) {
        this.optionCheck = optionCheck;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer id) {
        this.optionId = id;
    }

    public String getOptionDesc() {
        return optionDesc;
    }

    public void setOptionDesc(String optionDesc) {
        this.optionDesc = optionDesc;
    }

    public Integer getAddPrice() {
        return addPrice;
    }

    public void setAddPrice(Integer addPrice) {
        this.addPrice = addPrice;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getOptionQuantity() {
        return optionQuantity;
    }

    public void setOptionQuantity(Integer optionQuantity) {
        this.optionQuantity = optionQuantity;
    }

}