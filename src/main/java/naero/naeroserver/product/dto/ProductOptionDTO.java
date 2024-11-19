package naero.naeroserver.product.dto;

import naero.naeroserver.entity.product.TblProduct;

public class ProductOptionDTO {

    private TblProduct product;
    private Integer addPrice;
    private String optionDesc;
    private Integer optionQuantity;

    public ProductOptionDTO() {
    }

    public ProductOptionDTO(TblProduct product, Integer addPrice, String optionDesc, Integer optionQuantity) {
        this.product = product;
        this.addPrice = addPrice;
        this.optionDesc = optionDesc;
        this.optionQuantity = optionQuantity;
    }

    public TblProduct getProduct() {
        return product;
    }

    public void setProduct(TblProduct product) {
        this.product = product;
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

    public Integer getOptionQuantity() {
        return optionQuantity;
    }

    public void setOptionQuantity(Integer optionQuantity) {
        this.optionQuantity = optionQuantity;
    }

    @Override
    public String toString() {
        return "ProductOptionDTO{" +
                "product=" + product +
                ", addPrice=" + addPrice +
                ", optionDesc='" + optionDesc + '\'' +
                ", optionQuantity=" + optionQuantity +
                '}';
    }
}
