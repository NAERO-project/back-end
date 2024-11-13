package naero.naeroserver.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_category_medium")
public class TblCategoryMedium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medium_category_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "medium_category_name", length = 50)
    private String mediumCategoryName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "large_category_id", nullable = false)
    private TblCategoryLarge largeCategory;

    @OneToMany(mappedBy = "mediumCategory")
    private Set<TblCategorySmall> tblCategorySmalls = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMediumCategoryName() {
        return mediumCategoryName;
    }

    public void setMediumCategoryName(String mediumCategoryName) {
        this.mediumCategoryName = mediumCategoryName;
    }

    public TblCategoryLarge getLargeCategory() {
        return largeCategory;
    }

    public void setLargeCategory(TblCategoryLarge largeCategory) {
        this.largeCategory = largeCategory;
    }

    public Set<TblCategorySmall> getTblCategorySmalls() {
        return tblCategorySmalls;
    }

    public void setTblCategorySmalls(Set<TblCategorySmall> tblCategorySmalls) {
        this.tblCategorySmalls = tblCategorySmalls;
    }

}