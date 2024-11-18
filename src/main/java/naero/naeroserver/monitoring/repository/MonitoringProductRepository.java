package naero.naeroserver.monitoring.repository;

import naero.naeroserver.entity.product.TblProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public interface MonitoringProductRepository extends JpaRepository<TblProduct, Integer> {

    @Query("SELECT new map(pt.productName AS product_name, CAST(SUM(od.amount) AS double) AS total_amount) " +
            "FROM TblProduct pt " +
            "LEFT JOIN TblOption op ON pt.productId = op.productId " +
            "LEFT JOIN TblOrderDetail od ON op.optionId = od.optionId " +
            "WHERE od.createdAt BETWEEN :parsedStartInstant AND :parsedEndInstant " +
            "GROUP BY pt.productName " +
            "ORDER BY SUM(od.amount) DESC")
    List<Map<String, Double>> findCrossByProductAndSales(@Param("parsedStartInstant") Instant parsedStartInstant,
                                                         @Param("parsedEndInstant") Instant parsedEndInstant);

    @Query("SELECT new map(pt.productName AS product_name, CAST(SUM(od.count) AS double) AS total_count) " +
            "FROM TblProduct pt " +
            "LEFT JOIN TblOption op ON pt.productId = op.productId " +
            "LEFT JOIN TblOrderDetail od ON op.optionId = od.optionId " +
            "WHERE od.createdAt BETWEEN :parsedStartInstant AND :parsedEndInstant " +
            "GROUP BY pt.productName " +
            "ORDER BY SUM(od.count) DESC")
    List<Map<String, Double>> findCrossByProductAndQuantity(@Param("parsedStartInstant") Instant parsedStartInstant,
                                                            @Param("parsedEndInstant") Instant parsedEndInstant);

    @Query("SELECT new map(DATE(od.createdAt) AS order_date, SUM(od.amount) AS total_amount) " +
            "FROM TblProduct pt " +
            "LEFT JOIN TblOption op ON pt.productId = op.productId " +
            "LEFT JOIN TblOrderDetail od ON op.optionId = od.optionId " +
            "WHERE od.createdAt BETWEEN :parsedStartInstant AND :parsedEndInstant " +
            "AND pt.productName = :specification " +
            "GROUP BY DATE(od.createdAt) " +
            "ORDER BY DATE(od.createdAt)")
    List<Map<String, Object>> findSeriesByProductAndSales(@Param("parsedStartInstant") Instant parsedStartInstant,
                                                          @Param("parsedEndInstant") Instant parsedEndInstant,
                                                          @Param("specification") String specification);

    @Query("SELECT new map(DATE(od.createdAt) AS order_date, SUM(od.count) AS total_count) " +
            "FROM TblProduct pt " +
            "LEFT JOIN TblOption op ON pt.productId = op.productId " +
            "LEFT JOIN TblOrderDetail od ON op.optionId = od.optionId " +
            "WHERE od.createdAt BETWEEN :parsedStartInstant AND :parsedEndInstant " +
            "AND pt.productName = :specification " +
            "GROUP BY DATE(od.createdAt) " +
            "ORDER BY DATE(od.createdAt)")
    List<Map<String, Object>> findSeriesByProductAndQuantity(@Param("parsedStartInstant") Instant parsedStartInstant,
                                                             @Param("parsedEndInstant") Instant parsedEndInstant,
                                                             @Param("specification") String specification);
}
