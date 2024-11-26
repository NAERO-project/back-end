package naero.naeroserver.shipping.repository;

import naero.naeroserver.entity.ship.TblShipCom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TblShipComRepository extends JpaRepository<TblShipCom, Integer> {

    @Query("SELECT sc " +
            "FROM TblShipCom sc")
    List<TblShipCom> findAllShippingCompany();
}
