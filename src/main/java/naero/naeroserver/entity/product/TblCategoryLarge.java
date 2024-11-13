package naero.naeroserver.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_category_large")
public class TblCategoryLarge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "large_category_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "large_category_name", length = 50)
    private String largeCategoryName;

    @OneToMany(mappedBy = "largeCategory")
    private Set<TblCategoryMedium> tblCategoryMedia = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLargeCategoryName() {
        return largeCategoryName;
    }

    public void setLargeCategoryName(String largeCategoryName) {
        this.largeCategoryName = largeCategoryName;
    }

    public Set<TblCategoryMedium> getTblCategoryMedia() {
        return tblCategoryMedia;
    }

    public void setTblCategoryMedia(Set<TblCategoryMedium> tblCategoryMedia) {
        this.tblCategoryMedia = tblCategoryMedia;
    }

}