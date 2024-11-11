package naero.naeroserver.product.dto;

import naero.naeroserver.product.entity.CategoryMedium;

public class CategorySmallDTO {

    private int smallCategoryId;
    private String smallCategoryName;
    private CategoryMedium mediumCategoryId;

    public CategorySmallDTO() {
    }

    public CategorySmallDTO(int smallCategoryId, String smallCategoryName, CategoryMedium mediumCategoryId) {
        this.smallCategoryId = smallCategoryId;
        this.smallCategoryName = smallCategoryName;
        this.mediumCategoryId = mediumCategoryId;
    }

    public String getSmallCategoryName() {
        return smallCategoryName;
    }

    public void setSmallCategoryName(String smallCategoryName) {
        this.smallCategoryName = smallCategoryName;
    }

    public int getSmallCategoryId() {
        return smallCategoryId;
    }

    public void setSmallCategoryId(int smallCategoryId) {
        this.smallCategoryId = smallCategoryId;
    }

    public CategoryMedium getMediumCategoryId() {
        return mediumCategoryId;
    }

    public void setMediumCategoryId(CategoryMedium mediumCategoryId) {
        this.mediumCategoryId = mediumCategoryId;
    }

    @Override
    public String toString() {
        return "CategorySmallDTO{" +
                "smallCategoryId=" + smallCategoryId +
                ", smallCategoryName='" + smallCategoryName + '\'' +
                ", mediumCategoryId=" + mediumCategoryId +
                '}';
    }
}
