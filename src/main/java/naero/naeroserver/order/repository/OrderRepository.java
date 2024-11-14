package naero.naeroserver.order.repository;

import naero.naeroserver.entity.order.TblOrder;
import naero.naeroserver.entity.user.TblUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<TblOrder, Integer> {
    List<TblOrder> findByUser(TblUser user);

    TblOrder findTblOrderById(Integer userId);
}
