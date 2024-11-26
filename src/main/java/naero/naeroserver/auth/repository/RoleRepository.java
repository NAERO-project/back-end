package naero.naeroserver.auth.repository;

import naero.naeroserver.entity.auth.TblRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<TblRole, Integer> {
   TblRole findById(int id);
}
