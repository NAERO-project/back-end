package naero.naeroserver.product.dto;


public class CategoryMediumDTO {

    private int mediumCategoryId;
    private String mediumCategoryName;
    private CategoryLargeDTO largeCategoryId;

    public CategoryMediumDTO() {
    }

    public CategoryMediumDTO(int mediumCategoryId, String mediumCategoryName, CategoryLargeDTO largeCategoryId) {
        this.mediumCategoryId = mediumCategoryId;
        this.mediumCategoryName = mediumCategoryName;
        this.largeCategoryId = largeCategoryId;
    }

    public int getMediumCategoryId() {
        return mediumCategoryId;
    }

    public void setMediumCategoryId(int mediumCategoryId) {
        this.mediumCategoryId = mediumCategoryId;
    }

    public String getMediumCategoryName() {
        return mediumCategoryName;
    }

    public void setMediumCategoryName(String mediumCategoryName) {
        this.mediumCategoryName = mediumCategoryName;
    }

    public CategoryLargeDTO getLargeCategoryId() {
        return largeCategoryId;
    }

    public void setLargeCategoryId(CategoryLargeDTO largeCategoryId) {
        this.largeCategoryId = largeCategoryId;
    }

    @Override
    public String toString() {
        return "CategoryMediumDTO{" +
                "mediumCategoryId=" + mediumCategoryId +
                ", mediumCategoryName='" + mediumCategoryName + '\'' +
                ", largeCategoryId=" + largeCategoryId +
                '}';
    }
}
