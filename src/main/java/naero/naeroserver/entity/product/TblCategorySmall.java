package naero.naeroserver.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_category_small")
public class TblCategorySmall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "small_category_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "small_category_name", length = 50)
    private String smallCategoryName;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "medium_category_id", nullable = false)
    private TblCategoryMedium mediumCategory;

    @OneToMany(mappedBy = "smallCategory")
    private Set<TblProduct> tblProducts = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSmallCategoryName() {
        return smallCategoryName;
    }

    public void setSmallCategoryName(String smallCategoryName) {
        this.smallCategoryName = smallCategoryName;
    }

    public TblCategoryMedium getMediumCategory() {
        return mediumCategory;
    }

    public void setMediumCategory(TblCategoryMedium mediumCategory) {
        this.mediumCategory = mediumCategory;
    }

    public Set<TblProduct> getTblProducts() {
        return tblProducts;
    }

    public void setTblProducts(Set<TblProduct> tblProducts) {
        this.tblProducts = tblProducts;
    }

}