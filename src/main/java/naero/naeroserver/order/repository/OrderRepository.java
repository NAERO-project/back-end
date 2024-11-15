package naero.naeroserver.order.repository;

import naero.naeroserver.entity.order.TblOrder;
import naero.naeroserver.entity.order.TblOrderDetail;
import naero.naeroserver.entity.user.TblUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<TblOrder, Integer> {

    // 마이페이지 내 구매자별 주문 리스트 조회 (최신순으로)
    @Query("SELECT o FROM TblOrder o WHERE o.user = :user ORDER BY o.createdAt DESC")
    List<TblOrder> findByUserOrderByRecent(@Param("user") TblUser user);

    // 판매자 주문 건 조회
    @Query("SELECT o FROM TblOrder o " +
            "JOIN o.tblOrderDetails od " + // TblOrder와 TblOrderDetail의 관계
            "JOIN od.option op " + // TblOrderDetail과 TblOption의 관계
            "JOIN op.product p " + // TblOption과 TblProduct의 관계
            "WHERE p.producer.id = :producerId " +
            "ORDER BY o.createdAt DESC")
    List<TblOrder> findOrdersByProducerId(@Param("producerId") Integer producerId);

    // 관리자 모든 주문 건 조회(최신순으로)
    @Query("SELECT o FROM TblOrder o ORDER BY o.createdAt DESC")
    List<TblOrder> findAllOrderByRecent();
}
