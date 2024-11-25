package naero.naeroserver.order.dto;

public class OrderDetailProductDTO {
    private Integer orderDetailId;
    private Integer optionId;
    private Integer count;
    private Integer amount;
    private String productName;
    private String productImg;
    private String productThumbnail;
    private Integer smallCategoryId;
    private Integer shippingId;

    public OrderDetailProductDTO(Integer orderDetailId, Integer optionId, Integer count, Integer amount, String productName, String productImg, String productThumbnail, Integer smallCategoryId, Integer shippingId) {
        this.orderDetailId = orderDetailId;
        this.optionId = optionId;
        this.count = count;
        this.amount = amount;
        this.productName = productName;
        this.productImg = productImg;
        this.productThumbnail = productThumbnail;
        this.smallCategoryId = smallCategoryId;
        this.shippingId = shippingId;
    }

    // Getters and setters
    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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

    public Integer getSmallCategoryId() {
        return smallCategoryId;
    }

    public void setSmallCategoryId(Integer smallCategoryId) {
        this.smallCategoryId = smallCategoryId;
    }

    public String getProductThumbnail() {
        return productThumbnail;
    }

    public void setProductThumbnail(String productThumbnail) {
        this.productThumbnail = productThumbnail;
    }

    public Integer getShippingId() {
        return shippingId;
    }

    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    @Override
    public String toString() {
        return "OrderDetailProductDTO{" +
                "orderDetailId=" + orderDetailId +
                ", optionId=" + optionId +
                ", count=" + count +
                ", amount=" + amount +
                ", productName='" + productName + '\'' +
                ", productImg='" + productImg + '\'' +
                ", productThumbnail='" + productThumbnail + '\'' +
                ", smallCategoryId=" + smallCategoryId +
                ", shippingId=" + shippingId +
                '}';
    }
}