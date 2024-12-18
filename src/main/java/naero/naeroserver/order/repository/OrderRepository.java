package naero.naeroserver.order.repository;

import naero.naeroserver.entity.order.TblOrder;
import naero.naeroserver.entity.order.TblOrderDetail;
import naero.naeroserver.entity.product.TblProduct;
import naero.naeroserver.entity.user.TblProducer;
import naero.naeroserver.entity.user.TblUser;
import naero.naeroserver.order.dto.OrderDetailProductDTO;
import naero.naeroserver.product.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<TblOrder, Integer> {

    // 마이페이지 내 구매자별 주문 리스트 조회 (최신순으로)
    @Query("SELECT o FROM TblOrder o WHERE o.userId = :userId ORDER BY o.createdAt DESC")
    List<TblOrder> findByUserOrderByRecent(@Param("userId") Integer userId);    // 마이페이지 내 구매자별 주문 리스트 조회 (최신순으로)

    @Query("SELECT o FROM TblOrder o WHERE o.userId = :userId ORDER BY o.createdAt DESC")
    Page<TblOrder> findByUserOrderByRecentPaging(@Param("userId") Integer userId, Pageable paging);

    // 판매자 주문 건 조회(paging)
    @Query("SELECT o FROM TblOrder o " +
            "JOIN TblOrderDetail od ON o.orderId = od.orderId " + // TblOrder와 TblOrderDetail의 관계를 명시적으로 매핑
            "JOIN TblOption op ON od.optionId = op.optionId " + // TblOrderDetail과 TblOption의 관계
            "JOIN TblProduct p ON op.productId = p.productId " + // TblOption과 TblProduct의 관계
            "WHERE p.producerId = :producerId " +
            "ORDER BY o.createdAt DESC")
    Page<TblOrder> findOrdersByProducerId(@Param("producerId") Integer producerId, Pageable paging);

    // 판매자 주문 건 조회
    @Query("SELECT o FROM TblOrder o " +
            "JOIN TblOrderDetail od ON o.orderId = od.orderId " + // TblOrder와 TblOrderDetail의 관계를 명시적으로 매핑
            "JOIN TblOption op ON od.optionId = op.optionId " + // TblOrderDetail과 TblOption의 관계
            "JOIN TblProduct p ON op.productId = p.productId " + // TblOption과 TblProduct의 관계
            "WHERE p.producerId = :producerId " +
            "ORDER BY o.createdAt DESC")
    List<TblOrder> findOrdersByProducerId(@Param("producerId") Integer producerId);

    // 관리자 모든 주문 건 조회(최신순으로)
    @Query("SELECT o FROM TblOrder o ORDER BY o.createdAt DESC")
    List<TblOrder> findAllOrderByRecent();
}
