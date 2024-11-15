package naero.naeroserver.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tbl_category_medium")
public class TblCategoryMedium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medium_category_id", nullable = false)
    private Integer mediumCategoryId;

    @Size(max = 50)
    @Column(name = "medium_category_name", length = 50)
    private String mediumCategoryName;

    @Column(name = "large_category_id")
    private Integer largeCategoryId;

    public Integer getMediumCategoryId() {
        return mediumCategoryId;
    }

    public void setMediumCategoryId(Integer id) {
        this.mediumCategoryId = id;
    }

    public String getMediumCategoryName() {
        return mediumCategoryName;
    }

    public void setMediumCategoryName(String mediumCategoryName) {
        this.mediumCategoryName = mediumCategoryName;
    }

    public Integer getLargeCategoryId() {
        return largeCategoryId;
    }

    public void setLargeCategoryId(Integer largeCategoryId) {
        this.largeCategoryId = largeCategoryId;
    }

}