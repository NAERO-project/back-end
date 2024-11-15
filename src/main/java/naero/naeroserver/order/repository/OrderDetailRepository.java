package naero.naeroserver.order.repository;

import naero.naeroserver.entity.order.TblOrder;
import naero.naeroserver.entity.order.TblOrderDetail;
import naero.naeroserver.order.dto.OrderDetailDTO;
import naero.naeroserver.order.dto.OrderDetailProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<TblOrderDetail, Integer> {

    // 주문 리스트 내 주문번호 별 주문 상품 리스트 조회
    @Query("SELECT new naero.naeroserver.order.dto.OrderDetailProductDTO(od.orderId, o.optionId, od.count, od.amount, " +
            "p.productName, p.productImg, p.productThumbnail, o.productId) " +
            "FROM TblOrderDetail od " +
            "JOIN TblOption o ON od.optionId = o.optionId " + // TblOrderDetail에서 TblOption으로의 관계
            "JOIN TblProduct p ON o.productId = p.productId " + // TblOption에서 TblProduct로의 관계
            "WHERE od.orderId = :orderId")
    List<OrderDetailProductDTO> findOrderDetailWithProductByOrder(@Param("orderId") Integer orderId);

    // 주문 번호로 주문 상세 조회
    TblOrderDetail findByOrderId(Integer id);
}
