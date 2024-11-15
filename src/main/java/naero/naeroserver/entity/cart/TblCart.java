package naero.naeroserver.entity.cart;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import naero.naeroserver.entity.user.TblUser;

@Entity
@Table(name = "tbl_cart")
public class TblCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", nullable = false)
    private Integer cartId;

    @NotNull
    @Column(name = "option_id", nullable = false)
    private Integer optionId;

    @NotNull
    @Column(name = "count", nullable = false)
    private Integer count;

    @NotNull
    @Column(name = "price", nullable = false)
    private Integer price;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private TblUser user;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer id) {
        this.cartId = id;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public TblUser getUser() {
        return user;
    }

    public void setUser(TblUser user) {
        this.user = user;
    }

}