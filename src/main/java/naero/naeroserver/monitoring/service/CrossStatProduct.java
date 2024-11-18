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

    /* Method to get PRODUCT SALES AMOUNT statistics and process the top 3 with "others" */
    public List<Map<String, Object>> getCrossByProductAndSales(Instant parsedStartInstant, Instant parsedEndInstant) {
        log.info("[CrossStat] getCrossByProductAndSales() Start");

        // Fetch product sales statistics
        List<Map<String, Double>> salesStatistics = monitoringProductRepository.findCrossByProductAndSales(parsedStartInstant, parsedEndInstant);

        // Calculate the total sum of all amounts
        double overallTotalAmount = salesStatistics.stream()
                .mapToDouble(stat -> stat.get("total_amount"))
                .sum();

        // Sort and split the statistics into top 3 and "others"
        List<Map<String, Object>> result = new ArrayList<>();
        double othersTotalAmount = 0.0;

        for (int i = 0; i < salesStatistics.size(); i++) {
            Map<String, Double> stat = salesStatistics.get(i);
            if (i < 3) {
                // Add top 3 products with their ratio
                double totalAmount = stat.get("total_amount");
                double ratio = totalAmount / overallTotalAmount;
                result.add(Map.of(
                        "product_name", stat.get("product_name"),
                        "total_amount", totalAmount, // Corrected typo from "total_amout" to "total_amount"
                        "ratio", ratio
                ));
            } else {
                // Accumulate the rest as "others"
                othersTotalAmount += stat.get("total_amount");
            }
        }

        // Add "others" to the result if necessary
        if (othersTotalAmount > 0) {
            double othersRatio = othersTotalAmount / overallTotalAmount;
            result.add(Map.of(
                    "product_name", "others",
                    "total_amount", othersTotalAmount,
                    "ratio", othersRatio
            ));
        }
        System.out.println("[결과확인] result = " + result);
        return result;
    }

    /* Method to get PRODUCT SALES QUANTITY statistics and process the top 3 with "others" */
    public List<Map<String, Object>> getCrossByProductAndQuantity(Instant parsedStartInstant, Instant parsedEndInstant) {
        log.info("[CrossStat] getCrossByProductAndQuantity() Start");

        // Fetch product sales quantity
        List<Map<String, Double>> salesStatistics = monitoringProductRepository.findCrossByProductAndQuantity(parsedStartInstant, parsedEndInstant);

        // Calculate the total sum of all quantity
        double overallTotalCount = salesStatistics.stream()
                .mapToDouble(stat -> stat.get("total_count"))
                .sum();

        // Sort and split the statistics into top 3 and "others"
        List<Map<String, Object>> result = new ArrayList<>();
        double othersTotalCount = 0.0;

        for (int i = 0; i < salesStatistics.size(); i++) {
            Map<String, Double> stat = salesStatistics.get(i);
            if (i < 3) {
                // Add top 3 products with their ratio
                double totalCount = stat.get("total_count");
                double ratio = totalCount / overallTotalCount;
                result.add(Map.of(
                        "product_name", stat.get("product_name"),
                        "total_count", totalCount,
                        "ratio", ratio
                ));
            } else {
                // Accumulate the rest as "others"
                othersTotalCount += stat.get("total_count");
            }
        }

        // Add "others" to the result if necessary
        if (othersTotalCount > 0) {
            double othersRatio = othersTotalCount / overallTotalCount;
            result.add(Map.of(
                    "product_name", "others",
                    "total_count", othersTotalCount,
                    "ratio", othersRatio
            ));
        }
        System.out.println("[결과확인] result = " + result);
        return result;
    }

    /* Method to get PRODUCT LIKED statistics and process the top 3 with "others" */
    public List<Map<String, Object>> getCrossByProductAndLike(Instant parsedStartInstant, Instant parsedEndInstant) {
        log.info("[CrossStat] getCrossByProductAndLike() Start");

        // Fetch product sales quantity
        List<Map<String, Double>> salesStatistics = monitoringProductRepository.findCrossByProductAndLike(parsedStartInstant, parsedEndInstant);
        System.out.println("salesStatistics = " + salesStatistics);

        // Calculate the total sum of all quantity
        double overallTotalCount = salesStatistics.stream()
                .mapToDouble(stat -> stat.get("total_like"))
                .sum();

        // Sort and split the statistics into top 3 and "others"
        List<Map<String, Object>> result = new ArrayList<>();
        double othersTotalCount = 0.0;

        for (int i = 0; i < salesStatistics.size(); i++) {
            Map<String, Double> stat = salesStatistics.get(i);
            if (i < 3) {
                // Add top 3 products with their ratio
                double totalCount = stat.get("total_like");
                double ratio = totalCount / overallTotalCount;
                result.add(Map.of(
                        "product_name", stat.get("product_name"),
                        "total_like", totalCount,
                        "ratio", ratio
                ));
            } else {
                // Accumulate the rest as "others"
                othersTotalCount += stat.get("total_like");
            }
        }

        // Add "others" to the result if necessary
        if (othersTotalCount > 0) {
            double othersRatio = othersTotalCount / overallTotalCount;
            result.add(Map.of(
                    "product_name", "others",
                    "total_like", othersTotalCount,
                    "ratio", othersRatio
            ));
        }
        System.out.println("[결과확인] result = " + result);
        return result;
    }
}
