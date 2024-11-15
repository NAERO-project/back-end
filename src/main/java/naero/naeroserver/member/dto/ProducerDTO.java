package naero.naeroserver.member.dto;

public class ProducerDTO {
    private int producerId;
    private String busiNo;
    private String producerAdd;
    private String producerName;
    private String producerPhone;
    private ProducerGradeDTO producerGrade;
    private int deliveryFee;
    private int deliveryCrit;

    public ProducerDTO() {
    }

    public ProducerDTO(int producerId, String busiNo, String producerAdd, String producerName, String producerPhone, ProducerGradeDTO producerGrade, int deliveryFee, int deliveryCrit) {
        this.producerId = producerId;
        this.busiNo = busiNo;
        this.producerAdd = producerAdd;
        this.producerName = producerName;
        this.producerPhone = producerPhone;
        this.producerGrade = producerGrade;
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

    public ProducerGradeDTO getProducerGrade() {
        return producerGrade;
    }

    public void setProducerGrade(ProducerGradeDTO producerGrade) {
        this.producerGrade = producerGrade;
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
        return "ProducerDTO{" +
                "producerId=" + producerId +
                ", busiNo='" + busiNo + '\'' +
                ", producerAdd='" + producerAdd + '\'' +
                ", producerName='" + producerName + '\'' +
                ", producerPhone='" + producerPhone + '\'' +
                ", producerGrade=" + producerGrade +
                ", deliveryFee=" + deliveryFee +
                ", deliveryCrit=" + deliveryCrit +
                '}';
    }
}
