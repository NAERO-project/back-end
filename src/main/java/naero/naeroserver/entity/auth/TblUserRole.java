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
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private TblUser user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private TblRole role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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