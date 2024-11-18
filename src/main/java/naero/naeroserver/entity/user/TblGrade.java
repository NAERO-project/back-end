package naero.naeroserver.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tbl_grade")
public class TblGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id", nullable = false)
    private Integer gradeId;

    @Size(max = 20)
    @Column(name = "grade_name", length = 20)
    private String gradeName;

    @Column(name = "crit_exp")
    private Integer critExp;

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer id) {
        this.gradeId = id;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Integer getCritExp() {
        return critExp;
    }

    public void setCritExp(Integer critExp) {
        this.critExp = critExp;
    }

}