package naero.naeroserver.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="tbl_product")
public class Product {

    @Id
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private int productPrice;

    @Column(name = "product_thumbnail")
    private String productThumbnail;
    private String productImg;
    private String productDesc;
    private String productCreateAt;
    private String prodcutDeleteAt;
    private String productCheck;
    private int productQuantity;
    private int producerId;
    private int smallCategoryId;
}
