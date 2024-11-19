package naero.naeroserver.monitoring.service;

import naero.naeroserver.monitoring.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class MonitoringServiceStat {
    private static final Logger log = LoggerFactory.getLogger(MonitoringServiceStat.class);

    private final MonitoringProductRepository monitoringProductRepository;
    private final MonitoringProducerRepository monitoringProducerRepository;
    private final CrossStatProduct crossStatProduct;
    private final CrossStatProducer crossStatProducer;

    @Autowired
    public MonitoringServiceStat(MonitoringProductRepository monitoringProductRepository,
                                 MonitoringProducerRepository monitoringProducerRepository,
                                 CrossStatProduct crossStatProduct,
                                 CrossStatProducer crossStatProducer) {
        this.monitoringProductRepository = monitoringProductRepository;
        this.monitoringProducerRepository = monitoringProducerRepository;
        this.crossStatProduct = crossStatProduct;
        this.crossStatProducer = crossStatProducer;
    }

    /* 설명. 브랜드/상품별 횡단면 매출 통계 조회 */
    public List<Map<String, Object>> selectSalesStatisticsCross(String categoryOption, String indexOption,
                                                                Instant parsedStartInstant, Instant parsedEndInstant) {
        log.info("[MonitoringServiceStat] selectSalesStatisticsCross() Start");

        if ("브랜드".equals(categoryOption)) {
            return "매출액".equals(indexOption)
                    ? crossStatProducer.getCrossByProducerAndSales(parsedStartInstant, parsedEndInstant)
                    : "판매량".equals(indexOption)
                    ? crossStatProducer.getCrossByProducerAndQuantity(parsedStartInstant, parsedEndInstant)
                    : Collections.emptyList();
        } else if ("상품".equals(categoryOption)) {
            return "매출액".equals(indexOption)
                    ? crossStatProduct.getCrossByProductAndSales(parsedStartInstant, parsedEndInstant)
                    : "판매량".equals(indexOption)
                    ? crossStatProduct.getCrossByProductAndQuantity(parsedStartInstant, parsedEndInstant)
                    : Collections.emptyList();
        } else {
            log.warn("Invalid categoryOption or indexOption: categoryOption={}, indexOption={}", categoryOption, indexOption);
            return Collections.emptyList();
        }
    }

    /* 설명. 브랜드/상품별별 횡단면 선호도 통계 조회 */
    public List<Map<String, Object>> selectLikedStatisticsCross(String categoryOption, Instant parsedStartInstant,
                                                                Instant parsedEndInstant) {
        log.info("[MonitoringServiceStat] selectLikedStatisticsCross() Start with categoryOption: {}", categoryOption);

        switch (categoryOption) {
            case "브랜드":
                return crossStatProducer.getCrossByProducerAndLike(parsedStartInstant, parsedEndInstant);
            case "상품":
                return crossStatProduct.getCrossByProductAndLike(parsedStartInstant, parsedEndInstant);
            default:
                log.warn("Invalid categoryOption: categoryOption={}", categoryOption);
                throw new IllegalArgumentException("Invalid categoryOption: " + categoryOption);
        }
    }

    /* 설명. 브랜드/상품별 시계열 매출 통계 조회 */
    public List<Map<String, Object>> selectSalesStatisticsSeries(String categoryOption, String indexOption,
                                                                 Instant parsedStartInstant, Instant parsedEndInstant, String specification) {
        log.info("[MonitoringServiceStat] selectSalesStatisticsSeries() Start");

        if ("브랜드".equals(categoryOption)) {
            return "매출액".equals(indexOption)
                    ? monitoringProducerRepository.findSeriesByProducerAndSales(parsedStartInstant, parsedEndInstant, specification)
                    : "판매량".equals(indexOption)
                    ? monitoringProducerRepository.findSeriesByProducerAndQuantity(parsedStartInstant, parsedEndInstant, specification)
                    : Collections.emptyList();
        } else if ("상품".equals(categoryOption)) {
            return "매출액".equals(indexOption)
                    ? monitoringProductRepository.findSeriesByProductAndSales(parsedStartInstant, parsedEndInstant, specification)
                    : "판매량".equals(indexOption)
                    ? monitoringProductRepository.findSeriesByProductAndQuantity(parsedStartInstant, parsedEndInstant, specification)
                    : Collections.emptyList();
        } else {
            log.warn("Invalid categoryOption or indexOption: categoryOption={}, indexOption={}", categoryOption, indexOption);
            return Collections.emptyList();
        }
    }

    /* 설명. 브랜드/상별별 시계열 선호도 통계 조회 */
    public List<Map<String, Object>> selectLikedStatisticsSeries(String categoryOption, Instant parsedStartInstant,
                                                                 Instant parsedEndInstant, String specification) {
        log.info("[MonitoringServiceStat] selectLikedStatisticsSeries() Start");

        if ("브랜드".equals(categoryOption)) {
            return monitoringProducerRepository.findSeriesByProducerAndLikes(parsedStartInstant, parsedEndInstant, specification);
        } else if ("상품".equals(categoryOption)) {
            return monitoringProductRepository.findSeriesByProductAndLikes(parsedStartInstant, parsedEndInstant, specification);
        } else {
            log.warn("Invalid categoryOption: categoryOption={}", categoryOption);
            return Collections.emptyList();
        }
    }
}
