package naero.naeroserver.product.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_category_small")
public class CategorySmall {

    @Id
    @Column(name = "small_category_id")
    private int smallCategoryId;

    @Column(name = "small_category_name")
    private String smallCategoryName;

    @ManyToOne()
    @JoinColumn(name = "medium_category_id")
    private CategoryMedium mediumCategoryId;
}
