package naero.naeroserver.entity.auth;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_role")
public class TblRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Integer id;

    @Size(max = 20)
    @Column(name = "role_name", length = 20)
    private String roleName;

    @OneToMany(mappedBy = "role")
    private Set<TblUserRole> tblUserRoles = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<TblUserRole> getTblUserRoles() {
        return tblUserRoles;
    }

    public void setTblUserRoles(Set<TblUserRole> tblUserRoles) {
        this.tblUserRoles = tblUserRoles;
    }

}