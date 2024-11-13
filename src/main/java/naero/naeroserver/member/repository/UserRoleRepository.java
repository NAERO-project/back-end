package naero.naeroserver.member.repository;

import naero.naeroserver.entity.auth.TblUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<TblUserRole,Integer> {
    List<TblUserRole> findByUserId(Integer userId);
}
