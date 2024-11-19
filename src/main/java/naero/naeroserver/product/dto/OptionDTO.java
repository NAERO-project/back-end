package naero.naeroserver.product.dto;

public class OptionDTO {

    private int optionId;
    private String optionDesc;
    private int addPrice;
    private int productId;
    private int opeiontQuantity;

    public OptionDTO() {
    }

    public OptionDTO(int optionId, String optionDesc, int addPrice, int productId, int opeiontQuantity) {
        this.optionId = optionId;
        this.optionDesc = optionDesc;
        this.addPrice = addPrice;
        this.productId = productId;
        this.opeiontQuantity = opeiontQuantity;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getOptionDesc() {
        return optionDesc;
    }

    public void setOptionDesc(String optionDesc) {
        this.optionDesc = optionDesc;
    }

    public int getAddPrice() {
        return addPrice;
    }

    public void setAddPrice(int addPrice) {
        this.addPrice = addPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOpeiontQuantity() {
        return opeiontQuantity;
    }

    public void setOpeiontQuantity(int opeiontQuantity) {
        this.opeiontQuantity = opeiontQuantity;
    }

    @Override
    public String toString() {
        return "OptionDTO{" +
                "optionId=" + optionId +
                ", optionDesc='" + optionDesc + '\'' +
                ", addPrice=" + addPrice +
                ", productId=" + productId +
                ", opeiontQuantity=" + opeiontQuantity +
                '}';
    }
}
