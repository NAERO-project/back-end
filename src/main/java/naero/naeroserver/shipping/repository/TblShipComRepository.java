package naero.naeroserver.shipping.repository;

import naero.naeroserver.entity.ship.TblShipCom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblShipComRepository extends JpaRepository<TblShipCom, Integer> {
}
