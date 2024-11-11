package naero.naeroserver.product.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_category_medium")
public class CategoryMedium {

    @Id
    @Column(name = "medium_category_id")
    private int mediumCategoryId;

    @Column(name = "medium_category_name")
    private String mediumCategoryName;

    @ManyToOne
    @JoinColumn(name = "large_category_id")
    private CategoryLarge categoryLarge;
}
