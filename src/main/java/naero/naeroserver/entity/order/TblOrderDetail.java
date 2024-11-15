package naero.naeroserver.entity.order;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import naero.naeroserver.entity.product.TblOption;
import naero.naeroserver.entity.ship.TblShipping;

import java.time.Instant;

@Entity
@Table(name = "tbl_order_detail")
public class TblOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "option_id", nullable = false)
    private TblOption option;

    @NotNull
    @Column(name = "count", nullable = false)
    private Integer count;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_id")
    private TblShipping shipping;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private TblOrder order;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TblOption getOption() {
        return option;
    }

    public void setOption(TblOption option) {
        this.option = option;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public TblShipping getShipping() {
        return shipping;
    }

    public void setShipping(TblShipping shipping) {
        this.shipping = shipping;
    }

    public TblOrder getOrder() {
        return order;
    }

    public void setOrder(TblOrder order) {
        this.order = order;
    }

}