package naero.naeroserver.monitoring.repository;

import naero.naeroserver.entity.product.TblProduct;
import naero.naeroserver.entity.user.TblProducer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public interface MonitoringProducerRepository extends JpaRepository<TblProducer, Integer> {

    @Query("SELECT new map(pr.producerName AS producer_name, CAST(SUM(od.amount) AS double) AS total_amount) " +
            "FROM TblProducer pr " +
            "LEFT JOIN TblProduct pt ON pr.producerId = pt.producerId " +
            "LEFT JOIN TblOption op ON pt.productId = op.productId " +
            "LEFT JOIN TblOrderDetail od ON op.optionId = od.optionId " +
            "WHERE od.createdAt BETWEEN :parsedStartInstant AND :parsedEndInstant " +
            "GROUP BY pr.producerName " +
            "ORDER BY SUM(od.amount) DESC")
    List<Map<String, Double>> findCrossByProducerAndSales(@Param("parsedStartInstant") Instant parsedStartInstant,
                                                          @Param("parsedEndInstant") Instant parsedEndInstant);

    @Query("SELECT new map(pr.producerName AS producer_name, CAST(SUM(od.count) AS double) AS total_count) " +
            "FROM TblProducer pr " +
            "LEFT JOIN TblProduct pt ON pr.producerId = pt.producerId " +
            "LEFT JOIN TblOption op ON pt.productId = op.productId " +
            "LEFT JOIN TblOrderDetail od ON op.optionId = od.optionId " +
            "WHERE od.createdAt BETWEEN :parsedStartInstant AND :parsedEndInstant " +
            "GROUP BY pr.producerName " +
            "ORDER BY SUM(od.count) DESC")
    List<Map<String, Double>> findCrossByProducerAndQuantity(@Param("parsedStartInstant") Instant parsedStartInstant,
                                                             @Param("parsedEndInstant") Instant parsedEndInstant);

    @Query("SELECT new map(DATE(od.createdAt) AS order_date, SUM(od.amount) AS total_amount) " +
            "FROM TblProducer pr " +
            "LEFT JOIN TblProduct pt ON pr.producerId = pt.producerId " +
            "LEFT JOIN TblOption op ON pt.productId = op.productId " +
            "LEFT JOIN TblOrderDetail od ON op.optionId = od.optionId " +
            "WHERE od.createdAt BETWEEN :parsedStartInstant AND :parsedEndInstant " +
            "AND pr.producerName = :specification " +
            "GROUP BY DATE(od.createdAt) " +
            "ORDER BY DATE(od.createdAt)")
    List<Map<String, Object>> findSeriesByProducerAndSales(@Param("parsedStartInstant") Instant parsedStartInstant,
                                                           @Param("parsedEndInstant") Instant parsedEndInstant,
                                                           @Param("specification") String specification);

    @Query("SELECT new map(DATE(od.createdAt) AS order_date, SUM(od.count) AS total_count) " +
            "FROM TblProducer pr " +
            "LEFT JOIN TblProduct pt ON pr.producerId = pt.producerId " +
            "LEFT JOIN TblOption op ON pt.productId = op.productId " +
            "LEFT JOIN TblOrderDetail od ON op.optionId = od.optionId " +
            "WHERE od.createdAt BETWEEN :parsedStartInstant AND :parsedEndInstant " +
            "AND pr.producerName = :specification " +
            "GROUP BY DATE(od.createdAt) " +
            "ORDER BY DATE(od.createdAt)")
    List<Map<String, Object>> findSeriesByProducerAndQuantity(@Param("parsedStartInstant") Instant parsedStartInstant,
                                                              @Param("parsedEndInstant") Instant parsedEndInstant,
                                                              @Param("specification") String specification);

    @Query("SELECT new map(pr.producerName AS producer_name, CAST(COUNT(pr.producerName) AS double) AS total_like) " +
            "FROM TblProducer pr " +
            "LEFT JOIN TblLikedSeller lpr ON pr.producerId = lpr.producerId " +
            "WHERE lpr.brandLikeDate BETWEEN :parsedStartInstant AND :parsedEndInstant " +
            "GROUP BY pr.producerName " +
            "ORDER BY COUNT(pr.producerName) DESC")
    List<Map<String, Double>> findCrossByProducerAndLike(@Param("parsedStartInstant") Instant parsedStartInstant,
                                                         @Param("parsedEndInstant") Instant parsedEndInstant);

    @Query("SELECT new map(DATE(lpr.brandLikeDate) AS like_date, COUNT(pr.producerName) AS total_like) " +
            "FROM TblProducer pr " +
            "LEFT JOIN TblLikedSeller lpr ON pr.producerId = lpr.producerId " +
            "WHERE (lpr.brandLikeDate IS NOT NULL AND lpr.brandLikeDate BETWEEN :parsedStartInstant AND :parsedEndInstant) " +
            "AND pr.producerName = :specification " +
            "GROUP BY DATE(lpr.brandLikeDate), pr.producerName " +
            "ORDER BY DATE(lpr.brandLikeDate)")
    List<Map<String, Object>> findSeriesByProducerAndLikes(@Param("parsedStartInstant") Instant parsedStartInstant,
                                                           @Param("parsedEndInstant") Instant parsedEndInstant,
                                                           @Param("specification") String specification);
}
