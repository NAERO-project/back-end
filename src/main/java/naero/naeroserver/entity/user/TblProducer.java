package naero.naeroserver.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "tbl_producer")
public class TblProducer {
    @Id
    @Column(name = "producer_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "producer_id", nullable = false)
    private TblUser producer;

    @Size(max = 20)
    @Column(name = "busi_no", length = 20)
    private String busiNo;

    @Size(max = 255)
    @Column(name = "producer_add")
    private String producerAdd;

    @Size(max = 20)
    @Column(name = "producer_name", length = 20)
    private String producerName;

    @Size(max = 20)
    @Column(name = "producer_phone", length = 20)
    private String producerPhone;

    @ColumnDefault("1")
    @Column(name = "pgrade_id")
    private Integer pgrade;

    @Column(name = "delivery_fee")
    private Integer deliveryFee;

    @Column(name = "delivery_crit")
    private Integer deliveryCrit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TblUser getProducer() {
        return producer;
    }

    public void setProducer(TblUser producer) {
        this.producer = producer;
    }

    public String getBusiNo() {
        return busiNo;
    }

    public void setBusiNo(String busiNo) {
        this.busiNo = busiNo;
    }

    public String getProducerAdd() {
        return producerAdd;
    }

    public void setProducerAdd(String producerAdd) {
        this.producerAdd = producerAdd;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public String getProducerPhone() {
        return producerPhone;
    }

    public void setProducerPhone(String producerPhone) {
        this.producerPhone = producerPhone;
    }

    public Integer getPgrade() {
        return pgrade;
    }

    public void setPgrade(Integer pgrade) {
        this.pgrade = pgrade;
    }

    public Integer getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Integer deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Integer getDeliveryCrit() {
        return deliveryCrit;
    }

    public void setDeliveryCrit(Integer deliveryCrit) {
        this.deliveryCrit = deliveryCrit;
    }

}