package naero.naeroserver.monitoring.dto;

import naero.naeroserver.entity.user.TblProducerGrade;
import naero.naeroserver.member.dto.ProducerGradeDTO;

public class SimplifiedProducerDTO {
    private int producerId;
    private String busiNo;
    private String producerAdd;
    private String producerName;
    private String producerPhone;
    private String withStatus;
    private int deliveryFee;
    private int deliveryCrit;

    public SimplifiedProducerDTO() {
    }

    public SimplifiedProducerDTO(int producerId, String busiNo, String producerAdd,
                                 String producerName, String producerPhone,
                                 String withStatus, int deliveryFee, int deliveryCrit) {
        this.producerId = producerId;
        this.busiNo = busiNo;
        this.producerAdd = producerAdd;
        this.producerName = producerName;
        this.producerPhone = producerPhone;
        this.withStatus = withStatus;
        this.deliveryFee = deliveryFee;
        this.deliveryCrit = deliveryCrit;
    }

    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
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

    public String getWithStatus() {
        return withStatus;
    }

    public void setWithStatus(String withStatus) {
        this.withStatus = withStatus;
    }

    public int getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(int deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public int getDeliveryCrit() {
        return deliveryCrit;
    }

    public void setDeliveryCrit(int deliveryCrit) {
        this.deliveryCrit = deliveryCrit;
    }

    @Override
    public String toString() {
        return "SimplifiedProducerDTO{" +
                "producerId=" + producerId +
                ", busiNo='" + busiNo + '\'' +
                ", producerAdd='" + producerAdd + '\'' +
                ", producerName='" + producerName + '\'' +
                ", producerPhone='" + producerPhone + '\'' +
                ", withStatus='" + withStatus + '\'' +
                ", deliveryFee=" + deliveryFee +
                ", deliveryCrit=" + deliveryCrit +
                '}';
    }
}
