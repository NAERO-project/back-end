package naero.naeroserver.monitoring.service;

import naero.naeroserver.monitoring.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Service
public class MonitoringService {

    private static final Logger log = LoggerFactory.getLogger(MonitoringService.class);

    private final MonitoringOrderRepository monitoringOrderRepository;
    private final MonitoringUserRepository monitoringUserRepository;
    private final MonitoringItemsRepository monitoringItemsRepository;

    @Autowired
    public MonitoringService(MonitoringOrderRepository monitoringOrderRepository,
                             MonitoringUserRepository monitoringUserRepository,
                             MonitoringItemsRepository monitoringItemsRepository) {
        this.monitoringOrderRepository = monitoringOrderRepository;
        this.monitoringUserRepository = monitoringUserRepository;
        this.monitoringItemsRepository = monitoringItemsRepository;
    }

    /* 설명. 24시간 매출 총액 데이터 조회 */
    public Map<String, Object> selectTodaySalesAmount() {
        return fetchDataAndCalculate(
                "Sales Amount",
                () -> monitoringOrderRepository.findOneDayAgoSalesAmount(getInstant(24)),
                () -> monitoringOrderRepository.findTwoDaysAgoSalesAmount(getInstant(48), getInstant(24))
        );
    }

    /* 설명. 24시간 매출 수량 데이터 조회 */
    public Map<String, Object> selectTodaySalesQuantity() {
        return fetchDataAndCalculate(
                "Sales Quantity",
                () -> monitoringOrderRepository.findOneDayAgoSalesQuantity(getInstant(24)),
                () -> monitoringOrderRepository.findTwoDaysAgoSalesQuantity(getInstant(48), getInstant(24))
        );
    }

    /* 설명. 24시간 신규 회원 데이터 조회 */
    public Map<String, Object> selectTodayNewMembers() {
        return fetchDataAndCalculate(
                "New Members",
                () -> monitoringUserRepository.findOneDayAgoRegistMembers(getInstant(24)),
                () -> monitoringUserRepository.findTwoDaysAgoRegistMembers(getInstant(48), getInstant(24))
        );
    }

    /* 설명. 24시간 판매 상품 종류 데이터 조회 */
    public Map<String, Object> selectTodaySalesItems() {
        return fetchDataAndCalculate(
                "Sales Items",
                () -> monitoringItemsRepository.findOneDayAgoItems(getInstant(24)),
                () -> monitoringItemsRepository.findTwoDaysAgoItems(getInstant(48), getInstant(24))
        );
    }

    /* 설명. 반환값 메서드를 따로 작성하여 중복코딩을 없앰 */
    private Map<String, Object> fetchDataAndCalculate(String label, Supplier<Long> oneDayAgoSupplier,
                                                      Supplier<Long> twoDaysAgoSupplier) {
        log.info("[MonitoringService] selectToday{}() Start", label);

        long oneDayAgoValue = safeFetch(oneDayAgoSupplier);
        long twoDaysAgoValue = safeFetch(twoDaysAgoSupplier);

        double ratio = (oneDayAgoValue != 0) ? ((double) (oneDayAgoValue - twoDaysAgoValue) / oneDayAgoValue) : 0.0;

        Map<String, Object> result = new HashMap<>();
        result.put("Level", oneDayAgoValue);
        result.put("Ratio", ratio);

        log.info("{} Calculation Completed: Level={}, {}Ratio={}", label, oneDayAgoValue, ratio);

        return result;
    }

    /* 설명. 자바의 LocalDateTime과 엔티티/데이터베이스의 Instant/Timestamp의 포멧 전환 메서드*/
    private Instant getInstant(int hoursAgo) {
        return LocalDateTime.now().minusHours(hoursAgo).toInstant(ZoneOffset.UTC);
    }

    /* 설명. Supplier라는 빌트인 인터페이스를 이용하여 레포지터리 null 반환값 집중 처리 */
    private long safeFetch(Supplier<Long> supplier) {
        Long value = supplier.get();
        return (value != null) ? value : 0;
    }
}

