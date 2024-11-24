package naero.naeroserver.auth.repository;

import naero.naeroserver.entity.auth.TblRole;
import naero.naeroserver.entity.auth.TblUserRole;
import naero.naeroserver.entity.user.TblUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<TblUserRole, Integer> {
    void deleteByUserAndRole(TblUser user, TblRole role);
}
