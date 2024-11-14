package naero.naeroserver.order.repository;

import naero.naeroserver.entity.order.TblOrder;
import naero.naeroserver.entity.order.TblOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<TblOrderDetail, Integer> {
    List<TblOrderDetail> findAllByOrder(TblOrder order);
}
