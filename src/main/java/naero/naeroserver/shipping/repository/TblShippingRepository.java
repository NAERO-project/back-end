package naero.naeroserver.shipping.repository;

import naero.naeroserver.entity.ship.TblShipping;
import org.springframework.data.repository.CrudRepository;

public interface TblShippingRepository extends CrudRepository<TblShipping, Integer> {

    TblShipping findByTrackingNumber(String trackingNumber);
}
