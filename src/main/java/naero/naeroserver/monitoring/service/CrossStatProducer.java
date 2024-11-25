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

    /**
     * Helper method to process statistics and return the top 3 with an "others" category
     * */
    private List<Map<String, Object>> processTop3WithOthers(List<Map<String, Double>> statistics,
                                                            String valueKey,
                                                            String producerKey) {
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
                        "name", stat.get(producerKey),
                        "value", value,
                        "ratio", value / overallTotal
                ));
            } else {
                othersTotal += value;
            }
        }

        if (othersTotal > 0) {
            result.add(Map.of(
                    "name", "others",
                    "value", othersTotal,
                    "ratio", othersTotal / overallTotal
            ));
        }

        log.info("[CrossStat] Final result: {}", result);
        return result;
    }

    /**
     * Retrieve producer sales amount statistics and processes the top 3 with "others"
     * */
    public List<Map<String, Object>> getCrossByProducerAndSales(Instant parsedStartInstant, Instant parsedEndInstant) {
        log.info("[CrossStat] getCrossByProducerAndSales() Start");
        List<Map<String, Double>> salesStatistics = monitoringProducerRepository
                .findCrossByProducerAndSales(parsedStartInstant, parsedEndInstant);
        return processTop3WithOthers(salesStatistics, "total_amount", "producer_name");
    }

    /**
     * Retrieve producer sales quantity statistics and processes the top 3 with "others"
     * */
    public List<Map<String, Object>> getCrossByProducerAndQuantity(Instant parsedStartInstant, Instant parsedEndInstant) {
        log.info("[CrossStat] getCrossByProducerAndQuantity() Start");
        List<Map<String, Double>> quantityStatistics = monitoringProducerRepository
                .findCrossByProducerAndQuantity(parsedStartInstant, parsedEndInstant);
        return processTop3WithOthers(quantityStatistics, "total_count", "producer_name");
    }

    /**
     * Retrieve producer like statistics and processes the top 3 with "others"
     * */
    public List<Map<String, Object>> getCrossByProducerAndLike(Instant parsedStartInstant, Instant parsedEndInstant) {
        log.info("[CrossStat] getCrossByProducerAndLike() Start");
        List<Map<String, Double>> likeStatistcs = monitoringProducerRepository
                .findCrossByProducerAndLike(parsedStartInstant, parsedEndInstant);
        return processTop3WithOthers(likeStatistcs, "total_like", "producer_name");
    }
}
