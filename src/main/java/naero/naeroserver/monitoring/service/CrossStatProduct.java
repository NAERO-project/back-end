package naero.naeroserver.monitoring.service;

import naero.naeroserver.monitoring.repository.MonitoringProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CrossStatProduct {
    private static final Logger log = LoggerFactory.getLogger(CrossStatProduct.class);

    private final MonitoringProductRepository monitoringProductRepository;

    @Autowired
    public CrossStatProduct(MonitoringProductRepository monitoringProductRepository) {
        this.monitoringProductRepository = monitoringProductRepository;
    }

    /**
     * Helper method to process statistics and return the top 3 with an "others" category.
     */
    private List<Map<String, Object>> processTop3WithOthers(List<Map<String, Double>> statistics,
                                                            String valueKey,
                                                            String productKey) {
        double overallTotal = statistics.stream()
                .mapToDouble(stat -> stat.get(valueKey))
                .sum();

        List<Map<String, Object>> result = new ArrayList<>();
        double othersTotal = 0.0;

        for (int i = 0; i < statistics.size(); i++) {
            Map<String, Double> stat = statistics.get(i);
            double value = stat.get(valueKey);
            if (i < 3) {
                result.add(Map.of(
                        "name", stat.get(productKey),
                        "value", value,
                        "ratio", value / overallTotal
                ));
            } else {
                othersTotal += value;
            }
        }

        if (othersTotal > 0) {
            result.add(Map.of(
                    "name", "기타",
                    "value", othersTotal,
                    "ratio", othersTotal / overallTotal
            ));
        }

        log.info("[CrossStat] Final result: {}", result);
        return result;
    }

    /**
     * Retrieves product sales amount statistics and processes the top 3 with "others".
     */
    public List<Map<String, Object>> getCrossByProductAndSales(Instant parsedStartInstant, Instant parsedEndInstant) {
        log.info("[CrossStat] getCrossByProductAndSales() Start");
        List<Map<String, Double>> salesStatistics = monitoringProductRepository
                .findCrossByProductAndSales(parsedStartInstant, parsedEndInstant);
        return processTop3WithOthers(salesStatistics, "total_amount", "product_name");
    }

    /**
     * Retrieves product sales quantity statistics and processes the top 3 with "others".
     */
    public List<Map<String, Object>> getCrossByProductAndQuantity(Instant parsedStartInstant, Instant parsedEndInstant) {
        log.info("[CrossStat] getCrossByProductAndQuantity() Start");
        List<Map<String, Double>> quantityStatistics = monitoringProductRepository
                .findCrossByProductAndQuantity(parsedStartInstant, parsedEndInstant);
        return processTop3WithOthers(quantityStatistics, "total_count", "product_name");
    }

    /**
     * Retrieves product liked statistics and processes the top 3 with "others".
     */
    public List<Map<String, Object>> getCrossByProductAndLike(Instant parsedStartInstant, Instant parsedEndInstant) {
        log.info("[CrossStat] getCrossByProductAndLike() Start");
        List<Map<String, Double>> likeStatistics = monitoringProductRepository
                .findCrossByProductAndLike(parsedStartInstant, parsedEndInstant);
        return processTop3WithOthers(likeStatistics, "total_like", "product_name");
    }
}
