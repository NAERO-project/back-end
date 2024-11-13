package naero.naeroserver.shipping.repository;

import naero.naeroserver.entity.ship.TblShipping;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TblShippingRepository extends CrudRepository<TblShipping, Integer> {

    List<TblShipping> findByShippingId(int shippingId);
}
