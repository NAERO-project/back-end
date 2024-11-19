package naero.naeroserver.monitoring.repository;

import naero.naeroserver.entity.product.TblProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;

@Repository
public interface MonitoringItemsRepository extends JpaRepository<TblProduct, Integer> {

    @Query(value = "SELECT COUNT(DISTINCT pt.product_name) AS total_count " +
            "FROM tbl_product pt " +
            "LEFT JOIN tbl_option op ON pt.product_id = op.product_id " +
            "LEFT JOIN tbl_order_detail od ON op.option_id = od.option_id " +
            "WHERE od.created_at >= :yesterday", nativeQuery = true)
    Long findOneDayAgoItems(@Param("yesterday") Instant yesterday);

    @Query(value = "SELECT COUNT(DISTINCT pt.product_name) AS total_count " +
            "FROM tbl_product pt " +
            "LEFT JOIN tbl_option op ON pt.product_id = op.product_id " +
            "LEFT JOIN tbl_order_detail od ON op.option_id = od.option_id " +
            "WHERE od.created_at BETWEEN :twoDaysAgo AND :yesterday", nativeQuery = true)
    Long findTwoDaysAgoItems(@Param("twoDaysAgo") Instant twoDaysAgo, @Param("yesterday") Instant yesterday);

}
