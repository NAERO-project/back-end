package naero.naeroserver.order.repository;

import naero.naeroserver.entity.order.TblOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<TblOrderDetail, Integer> {
}
