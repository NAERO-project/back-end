package naero.naeroserver.product.dto;

import naero.naeroserver.entity.product.TblProduct;
import naero.naeroserver.entity.user.TblProducer;

public class ProducerProductDTO {

    private TblProducer producer;
    private TblProduct product;

    public ProducerProductDTO() {
    }

    public TblProducer getProducer() {
        return producer;
    }

    public void setProducer(TblProducer producer) {
        this.producer = producer;
    }

    public TblProduct getProduct() {
        return product;
    }

    public void setProduct(TblProduct product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProducerProductDTO{" +
                "producer=" + producer +
                ", product=" + product +
                '}';
    }
}
