package naero.naeroserver.order.repository;

import naero.naeroserver.entity.order.TblOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<TblOrder, Integer> {
}
