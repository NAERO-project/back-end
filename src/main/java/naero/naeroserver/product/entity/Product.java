package naero.naeroserver.product.entity;

import jakarta.persistence.*;

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

    @Column(name = "product_img")
    private String productImg;

    @Column(name = "product_desc")
    private String productDesc;

    @Column(name = "product_create_at")
    private String productCreateAt;

    @Column(name = "product_delete_at")
    private String prodcutDeleteAt;

    @Column(name = "product_check")
    private String productCheck;

    @Column(name = "product_quantity")
    private int productQuantity;

//    @ManyToOne
//    @Column(name = "producer_id")
//    @JoinColumn(name = "producer_id")
//    private Producer Producer;

    @Column(name = "producer_id")
    private int producerId;

    @ManyToOne
    @JoinColumn(name = "small_category_id")
    private CategorySmall CategorySmall;


}
