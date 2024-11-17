package naero.naeroserver.monitoring.service;

import naero.naeroserver.monitoring.repository.MonitoringProducerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CrossStatProducer {
    private static final Logger log = LoggerFactory.getLogger(CrossStatProducer.class);

    private final MonitoringProducerRepository monitoringProducerRepository;

    @Autowired
    public CrossStatProducer(MonitoringProducerRepository monitoringProducerRepository) {
        this.monitoringProducerRepository = monitoringProducerRepository;
    }

    /* Method to get PRODUCER SALES QUANTITY statistics and process the top 3 with "others" */
    public List<Map<String, Object>> getCrossByProducerAndSales(Instant parsedStartInstant, Instant parsedEndInstant) {
        log.info("[CrossStat] getCrossByProducerAndSales() start");

        // Fetch producer sales amount
        List<Map<String, Double>> salesStatistics = monitoringProducerRepository.findCrossByProducerAndSales(parsedStartInstant, parsedEndInstant);

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
                        "producer_name", stat.get("producer_name"),
                        "total_amount", totalAmount,
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
                    "producer_name", "others",
                    "total_amount", othersTotalAmount,
                    "ratio", othersRatio
            ));
        }
        System.out.println("[결과확인] result = " + result);
        return result;
    }

    /* Method to get PRODUCER SALES QUANTITY statistics and process the top 3 with "others" */
    public List<Map<String, Object>> getCrossByProducerAndQuantity(Instant parsedStartInstant, Instant parsedEndInstant) {
        log.info("[CrossStat] getCrossByProducerAndQuantity() start");

        // Fetch producer sales quantity
        List<Map<String, Double>> salesStatistics = monitoringProducerRepository.findCrossByProducerAndQuantity(parsedStartInstant, parsedEndInstant);

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
                        "producer_name", stat.get("producer_name"),
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
                    "producer_name", "others",
                    "total_count", othersTotalCount,
                    "ratio", othersRatio
            ));
        }
        System.out.println("[결과확인] result = " + result);
        return result;
    }
}
