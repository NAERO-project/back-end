package naero.naeroserver.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import naero.naeroserver.entity.liked.TblLikedProduct;
import naero.naeroserver.entity.inquiry.TblReview;
import naero.naeroserver.entity.user.TblProducer;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_product")
public class TblProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "product_name", length = 50)
    private String productName;

    @Column(name = "product_price")
    private Integer productPrice;

    @Size(max = 255)
    @Column(name = "product_thumbnail")
    private String productThumbnail;

    @Size(max = 255)
    @Column(name = "product_img")
    private String productImg;

    @Size(max = 255)
    @Column(name = "product_desc")
    private String productDesc;

    @Column(name = "product_create_at")
    private Instant productCreateAt;

    @Column(name = "product_delete_at")
    private Instant productDeleteAt;

    @Size(max = 1)
    @Column(name = "product_check", length = 1)
    private String productCheck;

    @Column(name = "product_quantity")
    private Integer productQuantity;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "producer_id", nullable = false, referencedColumnName = "producer_id")
    private TblProducer producer;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "small_category_id", nullable = false)
    private TblCategorySmall smallCategory;

    @OneToMany(mappedBy = "product")
    private Set<TblLikedProduct> tblLikedProducts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<TblOption> tblOptions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<TblReview> tblReviews = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductThumbnail() {
        return productThumbnail;
    }

    public void setProductThumbnail(String productThumbnail) {
        this.productThumbnail = productThumbnail;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Instant getProductCreateAt() {
        return productCreateAt;
    }

    public void setProductCreateAt(Instant productCreateAt) {
        this.productCreateAt = productCreateAt;
    }

    public Instant getProductDeleteAt() {
        return productDeleteAt;
    }

    public void setProductDeleteAt(Instant productDeleteAt) {
        this.productDeleteAt = productDeleteAt;
    }

    public String getProductCheck() {
        return productCheck;
    }

    public void setProductCheck(String productCheck) {
        this.productCheck = productCheck;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public TblProducer getProducer() {
        return producer;
    }

    public void setProducer(TblProducer producer) {
        this.producer = producer;
    }

    public TblCategorySmall getSmallCategory() {
        return smallCategory;
    }

    public void setSmallCategory(TblCategorySmall smallCategory) {
        this.smallCategory = smallCategory;
    }

    public Set<TblLikedProduct> getTblLikedProducts() {
        return tblLikedProducts;
    }

    public void setTblLikedProducts(Set<TblLikedProduct> tblLikedProducts) {
        this.tblLikedProducts = tblLikedProducts;
    }

    public Set<TblOption> getTblOptions() {
        return tblOptions;
    }

    public void setTblOptions(Set<TblOption> tblOptions) {
        this.tblOptions = tblOptions;
    }

    public Set<TblReview> getTblReviews() {
        return tblReviews;
    }

    public void setTblReviews(Set<TblReview> tblReviews) {
        this.tblReviews = tblReviews;
    }

}