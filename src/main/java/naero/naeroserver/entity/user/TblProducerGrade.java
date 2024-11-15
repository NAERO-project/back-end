package naero.naeroserver.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_producer_grade")
public class TblProducerGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pgrade_id", nullable = false)
    private Integer id;

    @Size(max = 20)
    @Column(name = "pgrade_name", length = 20)
    private String pgradeName;

    @Column(name = "crit_sales")
    private Integer critSales;

    @Column(name = "crit_review")
    private Integer critReview;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPgradeName() {
        return pgradeName;
    }

    public void setPgradeName(String pgradeName) {
        this.pgradeName = pgradeName;
    }

    public Integer getCritSales() {
        return critSales;
    }

    public void setCritSales(Integer critSales) {
        this.critSales = critSales;
    }

    public Integer getCritReview() {
        return critReview;
    }

    public void setCritReview(Integer critReview) {
        this.critReview = critReview;
    }
}