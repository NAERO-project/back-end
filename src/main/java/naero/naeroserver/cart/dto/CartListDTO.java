package naero.naeroserver.cart.dto;

public class CartListDTO {

   private Integer cartId;
   private Integer optionId;
   private Integer count;
   private Integer price;
   private Integer addPrice;
   private Integer optionQuantity;
   private String optionDesc;
   private Integer productId;
   private String productName;
   private Integer productPrice;
   private String productThumbnail;
   private String productImg;
   private String productCheck;

    public CartListDTO() {
    }

    public CartListDTO(Integer cartId, Integer optionId, Integer count, Integer price, Integer addPrice, Integer optionQuantity, String optionDesc, Integer productId, String productName, Integer productPrice, String productThumbnail, String productImg, String productCheck) {
        this.cartId = cartId;
        this.optionId = optionId;
        this.count = count;
        this.price = price;
        this.addPrice = addPrice;
        this.optionQuantity = optionQuantity;
        this.optionDesc = optionDesc;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productThumbnail = productThumbnail;
        this.productImg = productImg;
        this.productCheck = productCheck;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
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

    public Integer getAddPrice() {
        return addPrice;
    }

    public void setAddPrice(Integer addPrice) {
        this.addPrice = addPrice;
    }

    public Integer getOptionQuantity() {
        return optionQuantity;
    }

    public void setOptionQuantity(Integer optionQuantity) {
        this.optionQuantity = optionQuantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductThumbnail() {
        return productThumbnail;
    }

    public void setProductThumbnail(String productThumbnail) {
        this.productThumbnail = productThumbnail;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductCheck() {
        return productCheck;
    }

    public void setProductCheck(String productCheck) {
        this.productCheck = productCheck;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getOptionDesc() {
        return optionDesc;
    }

    public void setOptionDesc(String optionDesc) {
        this.optionDesc = optionDesc;
    }

    @Override
    public String toString() {
        return "CartListDTO{" +
                "cartId=" + cartId +
                ", optionId=" + optionId +
                ", count=" + count +
                ", price=" + price +
                ", addPrice=" + addPrice +
                ", optionQuantity=" + optionQuantity +
                ", optionDesc='" + optionDesc + '\'' +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productThumbnail='" + productThumbnail + '\'' +
                ", productImg='" + productImg + '\'' +
                ", productCheck='" + productCheck + '\'' +
                '}';
    }
}
