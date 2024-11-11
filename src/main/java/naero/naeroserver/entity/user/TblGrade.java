package naero.naeroserver.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_grade")
public class TblGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id", nullable = false)
    private Integer id;

    @Size(max = 20)
    @Column(name = "grade_name", length = 20)
    private String gradeName;

    @Column(name = "crit_exp")
    private Integer critExp;

    @OneToMany(mappedBy = "grade")
    private Set<TblUser> tblUsers = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Set<TblUser> getTblUsers() {
        return tblUsers;
    }

    public void setTblUsers(Set<TblUser> tblUsers) {
        this.tblUsers = tblUsers;
    }

}