package naero.naeroserver.monitoring.service;

import naero.naeroserver.monitoring.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
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

    /* 설명. 횡단면 브랜드/상품별 매출 통계 데이터 조회 */
    public List<Map<String, Object>> selectSalesStatisticsCross(String categoryOption, String indexOption,
                                                                Instant parsedStartInstant, Instant parsedEndInstant) {
        log.info("[MonitoringStat] selectSalesStatisticsCross() Start");

        switch (categoryOption) {
            case "브랜드":
                return "매출액".equals(indexOption)
                        ? crossStatProducer.getCrossByProducerAndSales(parsedStartInstant, parsedEndInstant)
                        : "판매량".equals(indexOption)
                        ? crossStatProducer.getCrossByProducerAndQuantity(parsedStartInstant, parsedEndInstant)
                        : new ArrayList<>();
            case "상품":
                return "매출액".equals(indexOption)
                        ? crossStatProduct.getCrossByProductAndSales(parsedStartInstant, parsedEndInstant)
                        : "판매량".equals(indexOption)
                        ? crossStatProduct.getCrossByProductAndQuantity(parsedStartInstant, parsedEndInstant)
                        : new ArrayList<>();
            default:
                log.warn("Invalid categoryOption or indexOption provided: categoryOption={}, indexOption={}",
                        categoryOption, indexOption);
                return new ArrayList<>();
        }
    }

    /* 설명. 횡단면 브랜드/상품별 찜 통계 데이터 조회 */
    public List<Map<String, Object>> selectLikedStatisticsCross(String categoryOption, Instant parsedStartInstant,
                                                                Instant parsedEndInstant) {
        log.info("[MonitoringServiceStat] selectLikedStatisticsCross() Start with categoryOption: {}", categoryOption);

        // Use constants or enums for categoryOption values if possible
        switch (categoryOption) {
            case "브랜드":
                return crossStatProducer.getCrossByProducerAndLike(parsedStartInstant, parsedEndInstant);
            case "상품":
                return crossStatProduct.getCrossByProductAndLike(parsedStartInstant, parsedEndInstant);
            default:
                log.warn("Invalid categoryOption provided: categoryOption={}", categoryOption);
                // Consider throwing an exception if invalid input should be handled explicitly
                throw new IllegalArgumentException("Invalid categoryOption: " + categoryOption);
        }
    }

    /* 설명. 시계열 매출 통계 데이터 조회 */
    public List<Map<String, Object>> selectSalesStatisticsSeries(String categoryOption, String indexOption,
                                                                 Instant parsedStartInstant, Instant parsedEndInstant, String specification) {
        log.info("[MonitoringStat] selectSalesStatisticsSeries() Start");

        switch (categoryOption) {
            case "브랜드":
                return "매출액".equals(indexOption)
                        ? monitoringProducerRepository.findSeriesByProducerAndSales(parsedStartInstant, parsedEndInstant, specification)
                        : "판매량".equals(indexOption)
                        ? monitoringProducerRepository.findSeriesByProducerAndQuantity(parsedStartInstant, parsedEndInstant, specification)
                        : new ArrayList<>();
            case "상품":
                return "매출액".equals(indexOption)
                        ? monitoringProductRepository.findSeriesByProductAndSales(parsedStartInstant, parsedEndInstant, specification)
                        : "판매량".equals(indexOption)
                        ? monitoringProductRepository.findSeriesByProductAndQuantity(parsedStartInstant, parsedEndInstant, specification)
                        : new ArrayList<>();
            default:
                log.warn("Invalid categoryOption or indexOption provided: categoryOption={}, indexOption={}",
                        categoryOption, indexOption);
                return new ArrayList<>();
        }
    }

    /* 설명. 시계열 찜 통계 데이터 조회 */
    public List<Map<String, Object>> selectLikedStatisticsSeries(String categoryOption, Instant parsedStartInstant,
                                                                 Instant parsedEndInstant, String specification) {
        log.info("[MonitoringStat] selectLikedStatisticsSeries() Start");

        switch (categoryOption) {
            case "브랜드":
                return monitoringProducerRepository.findSeriesByProducerAndLikes(parsedStartInstant, parsedEndInstant, specification);
            case "상품":
                return monitoringProductRepository.findSeriesByProductAndLikes(parsedStartInstant, parsedEndInstant, specification);
            default:
                log.warn("Invalid categoryOption provided: categoryOption={}", categoryOption);
                return new ArrayList<>();
        }
    }
}
