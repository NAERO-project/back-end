package naero.naeroserver.product.dto;

import naero.naeroserver.entity.product.TblCategoryMedium;

public class CategorySmallDTO {

    private Integer smallCategoryId;
    private String smallCategoryName;
    private Integer mediumCategoryId;

    public CategorySmallDTO() {
    }

    public CategorySmallDTO(Integer smallCategoryId, String smallCategoryName, Integer mediumCategoryId) {
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

    public Integer getSmallCategoryId() {
        return smallCategoryId;
    }

    public void setSmallCategoryId(Integer smallCategoryId) {
        this.smallCategoryId = smallCategoryId;
    }

    public Integer getMediumCategoryId() {
        return mediumCategoryId;
    }

    public void setMediumCategoryId(Integer mediumCategoryId) {
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
