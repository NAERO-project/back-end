package naero.naeroserver.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import naero.naeroserver.entity.cart.TblCart;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_option")
public class TblOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "option_desc")
    private String optionDesc;

    @Column(name = "add_price")
    private Integer addPrice;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private TblProduct product;

    @OneToMany(mappedBy = "option")
    private Set<TblCart> tblCarts = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public TblProduct getProduct() {
        return product;
    }

    public void setProduct(TblProduct product) {
        this.product = product;
    }

    public Set<TblCart> getTblCarts() {
        return tblCarts;
    }

    public void setTblCarts(Set<TblCart> tblCarts) {
        this.tblCarts = tblCarts;
    }

}