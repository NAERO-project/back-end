package naero.naeroserver.order.dto;

public class OrderPageProductDTO {

    private Integer optionId; //
    private Integer productId;
    private Integer producerId;
    private String producerName;
    private Integer deliveryFee;
    private Integer deliveryCrit;
    private String productName;
    private String productImg;
    private String productThumbnail;
    private Integer count; //
    private Integer amount; //
    private Integer addPrice;
    private String optionDesc;
    private Integer smallCategoryId;
    private String smallCategoryName;

    public OrderPageProductDTO() {
    }

    public OrderPageProductDTO(Integer optionId, Integer productId, Integer producerId, String producerName, Integer deliveryFee, Integer deliveryCrit, String productName, String productImg, String productThumbnail, Integer amount, Integer addPrice, String optionDesc, Integer smallCategoryId, String smallCategoryName) {
        this.optionId = optionId;
        this.productId = productId;
        this.producerId = producerId;
        this.producerName = producerName;
        this.deliveryFee = deliveryFee;
        this.deliveryCrit = deliveryCrit;
        this.productName = productName;
        this.productImg = productImg;
        this.productThumbnail = productThumbnail;
        this.amount = amount;
        this.addPrice = addPrice;
        this.optionDesc = optionDesc;
        this.smallCategoryId = smallCategoryId;
        this.smallCategoryName = smallCategoryName;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProducerId() {
        return producerId;
    }

    public void setProducerId(Integer producerId) {
        this.producerId = producerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductThumbnail() {
        return productThumbnail;
    }

    public void setProductThumbnail(String productThumbnail) {
        this.productThumbnail = productThumbnail;
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

    public Integer getAddPrice() {
        return addPrice;
    }

    public void setAddPrice(Integer addPrice) {
        this.addPrice = addPrice;
    }

    public String getOptionDesc() {
        return optionDesc;
    }

    public void setOptionDesc(String optionDesc) {
        this.optionDesc = optionDesc;
    }

    public Integer getSmallCategoryId() {
        return smallCategoryId;
    }

    public void setSmallCategoryId(Integer smallCategoryId) {
        this.smallCategoryId = smallCategoryId;
    }

    public String getSmallCategoryName() {
        return smallCategoryName;
    }

    public void setSmallCategoryName(String smallCategoryName) {
        this.smallCategoryName = smallCategoryName;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
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

    @Override
    public String toString() {
        return "OrderPageProductDTO{" +
                "optionId=" + optionId +
                ", productId=" + productId +
                ", producerId='" + producerId + '\'' +
                ", productName='" + productName + '\'' +
                ", productImg='" + productImg + '\'' +
                ", productThumbnail='" + productThumbnail + '\'' +
                ", count=" + count +
                ", amount=" + amount +
                ", addPrice=" + addPrice +
                ", optionDesc='" + optionDesc + '\'' +
                ", smallCategoryId=" + smallCategoryId +
                ", smallCategoryName='" + smallCategoryName + '\'' +
                '}';
    }
}
