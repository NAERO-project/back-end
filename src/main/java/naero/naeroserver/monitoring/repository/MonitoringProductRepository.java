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
//            "WHERE (od.createdAt IS NOT NULL AND od.createdAt BETWEEN :parsedStartInstant AND :parsedEndInstant) " +
            "WHERE od.createdAt BETWEEN :parsedStartInstant AND :parsedEndInstant " +
            "GROUP BY pt.productName " +
            "ORDER BY SUM(od.amount) DESC")
    List<Map<String, Double>> findCrossByProductAndSales(@Param("parsedStartInstant") Instant parsedStartInstant,
                                                         @Param("parsedEndInstant") Instant parsedEndInstant);

    @Query("SELECT new map(pt.productName AS product_name, CAST(SUM(od.count) AS double) AS total_count) " +
            "FROM TblProduct pt " +
            "LEFT JOIN TblOption op ON pt.productId = op.productId " +
            "LEFT JOIN TblOrderDetail od ON op.optionId = od.optionId " +
//            "WHERE (od.createdAt IS NOT NULL AND od.createdAt BETWEEN :parsedStartInstant AND :parsedEndInstant) " +
            "WHERE od.createdAt BETWEEN :parsedStartInstant AND :parsedEndInstant " +
            "GROUP BY pt.productName " +
            "ORDER BY SUM(od.count) DESC")
    List<Map<String, Double>> findCrossByProductAndQuantity(@Param("parsedStartInstant") Instant parsedStartInstant,
                                                            @Param("parsedEndInstant") Instant parsedEndInstant);

    @Query("SELECT new map(DATE(od.createdAt) AS count_date, SUM(od.amount) AS value) " +
            "FROM TblProduct pt " +
            "LEFT JOIN TblOption op ON pt.productId = op.productId " +
            "LEFT JOIN TblOrderDetail od ON op.optionId = od.optionId " +
//            "WHERE (od.createdAt IS NOT NULL AND od.createdAt BETWEEN :parsedStartInstant AND :parsedEndInstant) " +
            "WHERE od.createdAt BETWEEN :parsedStartInstant AND :parsedEndInstant " +
            "AND pt.productName = :specification " +
            "GROUP BY DATE(od.createdAt) " +
            "ORDER BY DATE(od.createdAt)")
    List<Map<String, Object>> findSeriesByProductAndSales(@Param("parsedStartInstant") Instant parsedStartInstant,
                                                          @Param("parsedEndInstant") Instant parsedEndInstant,
                                                          @Param("specification") String specification);

    @Query("SELECT new map(DATE(od.createdAt) AS count_date, SUM(od.count) AS value) " +
            "FROM TblProduct pt " +
            "LEFT JOIN TblOption op ON pt.productId = op.productId " +
            "LEFT JOIN TblOrderDetail od ON op.optionId = od.optionId " +
//            "WHERE (od.createdAt IS NOT NULL AND od.createdAt BETWEEN :parsedStartInstant AND :parsedEndInstant) " +
            "WHERE od.createdAt BETWEEN :parsedStartInstant AND :parsedEndInstant " +
            "AND pt.productName = :specification " +
            "GROUP BY DATE(od.createdAt) " +
            "ORDER BY DATE(od.createdAt)")
    List<Map<String, Object>> findSeriesByProductAndQuantity(@Param("parsedStartInstant") Instant parsedStartInstant,
                                                             @Param("parsedEndInstant") Instant parsedEndInstant,
                                                             @Param("specification") String specification);

    @Query("SELECT new map(pt.productName AS product_name, CAST(COUNT(pt.productName) AS double) AS total_like) " +
            "FROM TblProduct pt " +
            "LEFT JOIN TblLikedProduct lpt ON pt.productId = lpt.productId " +
//            "WHERE (lpt.productLikeDate IS NOT NULL AND lpt.productLikeDate BETWEEN :parsedStartInstant AND :parsedEndInstant) " +
            "WHERE lpt.productLikeDate BETWEEN :parsedStartInstant AND :parsedEndInstant " +
            "GROUP BY pt.productName " +
            "ORDER BY COUNT(pt.productName) DESC")
    List<Map<String, Double>> findCrossByProductAndLike(@Param("parsedStartInstant") Instant parsedStartInstant,
                                                        @Param("parsedEndInstant") Instant parsedEndInstant);

    @Query("SELECT new map(DATE(lpt.productLikeDate) AS count_date, COUNT(pt.productName) AS value) " +
            "FROM TblProduct pt " +
            "LEFT JOIN TblLikedProduct lpt ON pt.productId = lpt.productId " +
//            "WHERE (lpt.productLikeDate IS NOT NULL AND lpt.productLikeDate BETWEEN :parsedStartInstant AND :parsedEndInstant) " +
            "WHERE lpt.productLikeDate BETWEEN :parsedStartInstant AND :parsedEndInstant " +
            "AND pt.productName = :specification " +
            "GROUP BY DATE(lpt.productLikeDate), pt.productName " +
            "ORDER BY DATE(lpt.productLikeDate)")
    List<Map<String, Object>> findSeriesByProductAndLikes(@Param("parsedStartInstant") Instant parsedStartInstant,
                                                          @Param("parsedEndInstant") Instant parsedEndInstant,
                                                          @Param("specification") String specification);
}
