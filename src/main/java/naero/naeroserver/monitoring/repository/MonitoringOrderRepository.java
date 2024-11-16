package naero.naeroserver.monitoring.repository;

import naero.naeroserver.entity.order.TblOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;

public interface MonitoringOrderRepository extends JpaRepository<TblOrder, Integer> {

    @Query("SELECT SUM(o.orderTotalAmount) " +
            "FROM TblOrder o " +
            "WHERE o.createdAt >= :yesterday AND o.orderStatus = 'completed'")
    Long findOneDayAgoSalesAmount(@Param("yesterday") Instant yesterday);

    @Query("SELECT SUM(o.orderTotalAmount) " +
            "FROM TblOrder o " +
            "WHERE o.createdAt BETWEEN :twoDaysAgo AND :yesterday " +
            "AND o.orderStatus = 'completed'")
    Long findTwoDaysAgoSalesAmount(@Param("twoDaysAgo") Instant twoDaysAgo, @Param("yesterday") Instant yesterday);


    @Query("SELECT SUM(o.orderTotalCount) " +
            "FROM TblOrder o " +
            "WHERE o.createdAt >= :yesterday AND o.orderStatus = 'completed'")
    Integer findOneDayAgoSalesQuantity(@Param("yesterday") Instant yesterday);

    @Query("SELECT SUM(o.orderTotalCount) " +
            "FROM TblOrder o " +
            "WHERE o.createdAt BETWEEN :twoDaysAgo AND :yesterday " +
            "AND o.orderStatus = 'completed'")
    Integer findTwoDaysAgoSalesQuantity(@Param("twoDaysAgo") Instant twoDaysAgo, @Param("yesterday") Instant yesterday);
}

