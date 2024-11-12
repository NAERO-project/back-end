package naero.naeroserver.product.dto;

import naero.naeroserver.entity.product.TblCategoryLarge;

public class CategoryMediumDTO {

    private int mediumCategoryId;
    private String mediumCategoryName;
    private int largeCategoryId;

    public CategoryMediumDTO() {
    }

    public CategoryMediumDTO(int mediumCategoryId, String mediumCategoryName, int largeCategoryId) {
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

    public int getLargeCategoryId() {
        return largeCategoryId;
    }

    public void setLargeCategoryId(int largeCategoryId) {
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
