package naero.naeroserver.auth.repository;

import naero.naeroserver.entity.auth.TblAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<TblAuth, Integer> {
    TblAuth findByAuthId(Integer id);
}
