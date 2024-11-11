package naero.naeroserver.product.dto;

import naero.naeroserver.product.entity.CategoryLarge;

public class CategoryMediumDTO {

    private int mediumCategoryId;
    private String mediumCategoryName;
    private CategoryLarge largeCategoryId;

    public CategoryMediumDTO() {
    }

    public CategoryMediumDTO(int mediumCategoryId, String mediumCategoryName, CategoryLarge largeCategoryId) {
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

    public CategoryLarge getLargeCategoryId() {
        return largeCategoryId;
    }

    public void setLargeCategoryId(CategoryLarge largeCategoryId) {
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
