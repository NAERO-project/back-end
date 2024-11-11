package naero.naeroserver.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_category_large")
public class CategoryLarge {

    @Id
    @Column(name = "large_category_id")
    private int largeCategoryId;

    @Column(name = "large_category_name")
    private String largeCategoryName;
}
