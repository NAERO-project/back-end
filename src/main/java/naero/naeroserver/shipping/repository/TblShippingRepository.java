package naero.naeroserver.shipping.repository;

import naero.naeroserver.entity.ship.TblShipping;
import org.springframework.data.repository.CrudRepository;

public interface TblShippingRepository extends CrudRepository<TblShipping, Integer> {

    /* 설명. 송장번호를 통한 배송상세 조회 */
    TblShipping findByTrackingNumber(String trackingNumber);

    TblShipping findByShippingId(Integer shippingId);
}
