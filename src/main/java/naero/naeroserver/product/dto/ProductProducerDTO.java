package naero.naeroserver.product.dto;

import naero.naeroserver.entity.product.TblProduct;

public class ProductProducerDTO {

    private Integer producerId;
    private String producerName;


    public ProductProducerDTO() {
    }

    public ProductProducerDTO(Integer producerId, String producerName) {
        this.producerId = producerId;
        this.producerName = producerName;
    }

    public Integer getProducerId() {
        return producerId;
    }

    public void setProducerId(Integer producerId) {
        this.producerId = producerId;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    @Override
    public String toString() {
        return "ProductProducerDTO{" +
                "producerId=" + producerId +
                ", producerName='" + producerName + '\'' +
                '}';
    }
}