package naero.naeroserver.order.repository;

import naero.naeroserver.entity.order.TblPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<TblPayment, Integer> {
}
