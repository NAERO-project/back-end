package naero.naeroserver.product.dto;

import java.util.List;

public class ProductDTO {

    private int productId;
    private String productName;
    private int productPrice;
    private String productThumbnail;
    private String productImg;
    private String productDesc;
    private String productCreateAt;
    private String productDeleteAt;
    private String productCheck;
    private int productQuantity;
    private int producerId;
    private CategorySmallDTO smallCategory;
    private List<OptionDTO> options;

    public ProductDTO() {
    }

    public ProductDTO(int productId, String productName, int productPrice, String productThumbnail, String productImg, String productDesc, String productCreateAt, String productDeleteAt, String productCheck, int productQuantity, int producerId, CategorySmallDTO smallCategory, List<OptionDTO> options) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productThumbnail = productThumbnail;
        this.productImg = productImg;
        this.productDesc = productDesc;
        this.productCreateAt = productCreateAt;
        this.productDeleteAt = productDeleteAt;
        this.productCheck = productCheck;
        this.productQuantity = productQuantity;
        this.producerId = producerId;
        this.smallCategory = smallCategory;
        this.options = options;
    }

    public ProductDTO(int productId, String productName, int productPrice, String productThumbnail, String productImg, String productDesc, String productCreateAt, String productDeleteAt, String productCheck, int productQuantity, int producerId, CategorySmallDTO smallCategory) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productThumbnail = productThumbnail;
        this.productImg = productImg;
        this.productDesc = productDesc;
        this.productCreateAt = productCreateAt;
        this.productDeleteAt = productDeleteAt;
        this.productCheck = productCheck;
        this.productQuantity = productQuantity;
        this.producerId = producerId;
        this.smallCategory = smallCategory;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
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

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductCreateAt() {
        return productCreateAt;
    }

    public void setProductCreateAt(String productCreateAt) {
        this.productCreateAt = productCreateAt;
    }

    public String getProductDeleteAt() {
        return productDeleteAt;
    }

    public void setProductDeleteAt(String productDeleteAt) {
        this.productDeleteAt = productDeleteAt;
    }

    public String getProductCheck() {
        return productCheck;
    }

    public void setProductCheck(String productCheck) {
        this.productCheck = productCheck;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    public CategorySmallDTO getSmallCategory() {
        return smallCategory;
    }

    public void setSmallCategory(CategorySmallDTO smallCategory) {
        this.smallCategory = smallCategory;
    }

    public List<OptionDTO> getOptions() {
        return options;
    }

    public void setOptions(List<OptionDTO> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productThumbnail='" + productThumbnail + '\'' +
                ", productImg='" + productImg + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", productCreateAt='" + productCreateAt + '\'' +
                ", productDeleteAt='" + productDeleteAt + '\'' +
                ", productCheck='" + productCheck + '\'' +
                ", productQuantity=" + productQuantity +
                ", producerId=" + producerId +
                ", smallCategory=" + smallCategory +
                ", options=" + options +
                '}';
    }
}
