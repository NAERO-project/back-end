package naero.naeroserver.product.dto;

import naero.naeroserver.entity.product.TblCategoryMedium;

public class CategorySmallDTO {

    private int smallCategoryId;
    private String smallCategoryName;
    private int mediumCategoryId;

    public CategorySmallDTO() {
    }

    public CategorySmallDTO(int smallCategoryId, String smallCategoryName, int mediumCategoryId) {
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

    public int getMediumCategoryId() {
        return mediumCategoryId;
    }

    public void setMediumCategoryId(int mediumCategoryId) {
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
