package naero.naeroserver.order.dto;

public class OrderDetailProductDTO {
    private int orderDetailId;
    private int optionId;
    private int count;
    private int amount;
    private String productName;
    private String productImg;
    private String productThumbnail;
    private int smallCategoryId;

    public OrderDetailProductDTO(int orderDetailId, int optionId, int count, int amount, String productName, String productImg, String productThumbnail, int smallCategoryId) {
        this.orderDetailId = orderDetailId;
        this.optionId = optionId;
        this.count = count;
        this.amount = amount;
        this.productName = productName;
        this.productImg = productImg;
        this.productThumbnail = productThumbnail;
        this.smallCategoryId = smallCategoryId;
    }

    // Getters and setters
    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
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

    public int getSmallCategoryId() {
        return smallCategoryId;
    }

    public void setSmallCategoryId(int smallCategoryId) {
        this.smallCategoryId = smallCategoryId;
    }

    public String getProductThumbnail() {
        return productThumbnail;
    }

    public void setProductThumbnail(String productThumbnail) {
        this.productThumbnail = productThumbnail;
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
                '}';
    }
}