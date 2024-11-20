package naero.naeroserver.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "tbl_product")
public class TblProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Integer productId;

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
    @ColumnDefault("'Y'")
    @Column(name = "product_check", length = 1)
    private String productCheck;

    @NotNull
    @Column(name = "producer_id", nullable = false)
    private Integer producerId;

    @OneToMany
    @JoinColumn(name="product_id")
    private List<TblOption> options;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "small_category_id", nullable = false)
    private TblCategorySmall smallCategory;

    public List<TblOption> getOptions() {
        return options;
    }

    public void setOptions(List<TblOption> options) {
        this.options = options;
    }
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer id) {
        this.productId = id;
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

    public Integer getProducerId() {
        return producerId;
    }

    public void setProducerId(Integer producerId) {
        this.producerId = producerId;
    }

    public @NotNull TblCategorySmall getSmallCategory() {
        return smallCategory;
    }

    public void setSmallCategory(@NotNull TblCategorySmall smallCategory) {
        this.smallCategory = smallCategory;
    }
}