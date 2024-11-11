package naero.naeroserver.product.dto;

public class CategoryLargeDTO {

    private int largeCategoryId;
    private String largeCategoryName;

    public CategoryLargeDTO() {
    }

    public CategoryLargeDTO(int largeCategoryId, String largeCategoryName) {
        this.largeCategoryId = largeCategoryId;
        this.largeCategoryName = largeCategoryName;
    }

    public int getLargeCategoryId() {
        return largeCategoryId;
    }

    public void setLargeCategoryId(int largeCategoryId) {
        this.largeCategoryId = largeCategoryId;
    }

    public String getLargeCategoryName() {
        return largeCategoryName;
    }

    public void setLargeCategoryName(String largeCategoryName) {
        this.largeCategoryName = largeCategoryName;
    }

    @Override
    public String toString() {
        return "CategoryLargeDTO{" +
                "largeCategoryId=" + largeCategoryId +
                ", largeCategoryName='" + largeCategoryName + '\'' +
                '}';
    }
}
