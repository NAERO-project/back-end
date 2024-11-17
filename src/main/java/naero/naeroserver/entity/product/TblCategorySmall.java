package naero.naeroserver.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tbl_category_small")
public class TblCategorySmall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "small_category_id", nullable = false)
    private Integer smallCategoryId;

    @Size(max = 50)
    @Column(name = "small_category_name", length = 50)
    private String smallCategoryName;

    @Column(name = "medium_category_id")
    private Integer mediumCategoryId;

    public Integer getSmallCategoryId() {
        return smallCategoryId;
    }

    public void setSmallCategoryId(Integer id) {
        this.smallCategoryId = id;
    }

    public String getSmallCategoryName() {
        return smallCategoryName;
    }

    public void setSmallCategoryName(String smallCategoryName) {
        this.smallCategoryName = smallCategoryName;
    }

    public Integer getMediumCategoryId() {
        return mediumCategoryId;
    }

    public void setMediumCategoryId(Integer mediumCategoryId) {
        this.mediumCategoryId = mediumCategoryId;
    }

}