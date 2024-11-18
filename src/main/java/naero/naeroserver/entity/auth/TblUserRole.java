package naero.naeroserver.entity.auth;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import naero.naeroserver.entity.user.TblUser;

@Entity
@Table(name = "tbl_user_role")
public class TblUserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id", nullable = false)
    private Integer userRoleId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private TblUser user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private TblRole role;

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer id) {
        this.userRoleId = id;
    }

    public TblUser getUser() {
        return user;
    }

    public void setUser(TblUser user) {
        this.user = user;
    }

    public TblRole getRole() {
        return role;
    }

    public void setRole(TblRole role) {
        this.role = role;
    }

}