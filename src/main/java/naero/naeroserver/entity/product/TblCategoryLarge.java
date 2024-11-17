package naero.naeroserver.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tbl_category_large")
public class TblCategoryLarge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "large_category_id", nullable = false)
    private Integer largeCategoryId;

    @Size(max = 50)
    @Column(name = "large_category_name", length = 50)
    private String largeCategoryName;

    public Integer getLargeCategoryId() {
        return largeCategoryId;
    }

    public void setLargeCategoryId(Integer id) {
        this.largeCategoryId = id;
    }

    public String getLargeCategoryName() {
        return largeCategoryName;
    }

    public void setLargeCategoryName(String largeCategoryName) {
        this.largeCategoryName = largeCategoryName;
    }

}