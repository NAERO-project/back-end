package naero.naeroserver.product.dto;

public class OptionDTO {

    private Integer optionId;
    private String optionDesc;
    private Integer addPrice;
    private Integer productId;
    private Integer optionQuantity;

    public OptionDTO() {
    }

    public OptionDTO(Integer optionId, String optionDesc, Integer addPrice, Integer productId, Integer optionQuantity) {
        this.optionId = optionId;
        this.optionDesc = optionDesc;
        this.addPrice = addPrice;
        this.productId = productId;
        this.optionQuantity = optionQuantity;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public String getOptionDesc() {
        return optionDesc;
    }

    public void setOptionDesc(String optionDesc) {
        this.optionDesc = optionDesc;
    }

    public Integer getAddPrice() {
        return addPrice;
    }

    public void setAddPrice(Integer addPrice) {
        this.addPrice = addPrice;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getOptionQuantity() {
        return optionQuantity;
    }

    public void setOptionQuantity(Integer optionQuantity) {
        this.optionQuantity = optionQuantity;
    }

    @Override
    public String toString() {
        return "OptionDTO{" +
                "optionId=" + optionId +
                ", optionDesc='" + optionDesc + '\'' +
                ", addPrice=" + addPrice +
                ", productId=" + productId +
                ", optionQuantity=" + optionQuantity +
                '}';
    }
}
