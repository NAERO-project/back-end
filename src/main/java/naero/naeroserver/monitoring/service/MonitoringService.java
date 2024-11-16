package naero.naeroserver.monitoring.service;

import naero.naeroserver.monitoring.repository.MonitoringOrderRepository;
import naero.naeroserver.monitoring.repository.MonitoringUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

@Service
public class MonitoringService {

    private static final Logger log = LoggerFactory.getLogger(MonitoringService.class);

    private MonitoringOrderRepository monitoringOrderRepository;
    private MonitoringUserRepository monitoringUserRepository;

    @Autowired
    public MonitoringService(MonitoringOrderRepository monitoringOrderRepository,
                             MonitoringUserRepository monitoringUserRepository) {
        this.monitoringOrderRepository = monitoringOrderRepository;
        this.monitoringUserRepository = monitoringUserRepository;
    }

    /* 설명. 24시간 매출 총액 데이터 조회 */
    public Map<String, Object> selectTodaySalesAmount() {
        log.info("[MonitoringService] selectTodaySalesAmount() Start");

        // Calculate time intervals and convert to Instant
        LocalDateTime now = LocalDateTime.now();
        Instant yesterday = now.minusHours(24).toInstant(ZoneOffset.UTC);
        Instant beforeYesterday = now.minusHours(48).toInstant(ZoneOffset.UTC);

        // Fetch the sales amounts
        Long oneDayAgoSalesAmount = monitoringOrderRepository.findOneDayAgoSalesAmount(yesterday);
        Long twoDaysAgoSalesAmount = monitoringOrderRepository.findTwoDaysAgoSalesAmount(beforeYesterday, yesterday);

        // Handle null values
        if (oneDayAgoSalesAmount == null) oneDayAgoSalesAmount = 0L;
        if (twoDaysAgoSalesAmount == null) twoDaysAgoSalesAmount = 0L;

        // Calculate ratio
        double oneDayAgoSalesAmountRatio = (oneDayAgoSalesAmount != 0) ?
                ((double) (oneDayAgoSalesAmount - twoDaysAgoSalesAmount) / oneDayAgoSalesAmount) : 0.0;

        // Prepare the result
        Map<String, Object> result = new HashMap<>();
        result.put("oneDayAgoSalesAmountLevel", oneDayAgoSalesAmount);
        result.put("oneDayAgoSalesAmountRatio", oneDayAgoSalesAmountRatio);

        log.info("Sales Amount Calculation Completed: oneDayAgoSalesAmountLevel={}, oneDayAgoSalesAmountRatio={}",
                oneDayAgoSalesAmount, oneDayAgoSalesAmountRatio);

        return result;
    }

    /* 설명. 24시간 매출 수량 데이터 조회 */
    public Object selectTodaySalesQuantity() {
        log.info("[MonitoringService] selectTodaySalesQuantity() Start");

        // Calculate time intervals and convert to Instant
        LocalDateTime now = LocalDateTime.now();
        Instant yesterday = now.minusHours(24).toInstant(ZoneOffset.UTC);
        Instant beforeYesterday = now.minusHours(48).toInstant(ZoneOffset.UTC);

        // Fetch the sales quantity
        Integer oneDayAgoSalesQuantity = monitoringOrderRepository.findOneDayAgoSalesQuantity(yesterday);
        Integer twoDaysAgoSalesQuantity = monitoringOrderRepository.findTwoDaysAgoSalesQuantity(beforeYesterday, yesterday);

        // Handle null values
        if (oneDayAgoSalesQuantity == null) oneDayAgoSalesQuantity = 0;
        if (twoDaysAgoSalesQuantity == null) twoDaysAgoSalesQuantity = 0;

        // Calculate ratio
        double oneDayAgoSalesQuantityRatio = (oneDayAgoSalesQuantity != 0 ) ?
                ((double) (oneDayAgoSalesQuantity - twoDaysAgoSalesQuantity) / oneDayAgoSalesQuantity) : 0.0;

        // Prepare the result
        Map<String, Object> result = new HashMap<>();
        result.put("oneDayAgoSalesQuantityLevel", oneDayAgoSalesQuantity);
        result.put("oneDayAgoSalesQuantityRatio", oneDayAgoSalesQuantityRatio);

        log.info("Sales Quantity Calculation Completed: oneDayAgoSalesQuantityLevel={}, oneDayAgoSalesQuantityRatio={}",
                oneDayAgoSalesQuantity, oneDayAgoSalesQuantityRatio);

        return result;
    }

    public Object selectTodayNewMembers() {
        log.info("[MonitoringService] selectTodayNewMembers() Start");

        // Calculate time intervals and convert to Instant
        LocalDateTime now = LocalDateTime.now();
        Instant yesterday = now.minusHours(24).toInstant(ZoneOffset.UTC);
        Instant beforeYesterday = now.minusHours(48).toInstant(ZoneOffset.UTC);

        // Fetch the member quantity
        Integer oneDayAgoRegistMembers = monitoringUserRepository.findOneDayAgoRegistMembers(yesterday);
        Integer twoDaysAgoRegistMembers = monitoringUserRepository.findTwoDaysAgoRegistMembers(beforeYesterday, yesterday);

        // Handle null values
        if (oneDayAgoRegistMembers == null) oneDayAgoRegistMembers = 0;
        if (twoDaysAgoRegistMembers == null) twoDaysAgoRegistMembers = 0;

        // Calculate ratio
        double oneDayAgoRegistMembersRatio = (oneDayAgoRegistMembers != 0) ?
                ((double) (oneDayAgoRegistMembers - twoDaysAgoRegistMembers) / oneDayAgoRegistMembers) : 0.0;

        // Prepare the result
        Map<String, Object> result = new HashMap<>();
        result.put("oneDayAgoRegistMembersLevel", oneDayAgoRegistMembers);
        result.put("twoDaysAgoRegistMembers", twoDaysAgoRegistMembers);

        log.info("Newly Registered Members Calculation Completed: oneDayAgoResgistMembersLevel={}, oneDayAgoRegistMembersRatio={}",
                oneDayAgoRegistMembers, oneDayAgoRegistMembersRatio);

        return result;
    }

    public Object selectTodaySalesItems() {


        return null;
    }

    public Object selectSalesStatisticsCross(String categoryOption, String indexOption,
                                             String startDate, String endDate, String specification) {
        return null;
    }

    public Object selectSalesStatisticsSeries(String categoryOption, String indexOption,
                                              String startDate, String endDate, String specification) {
        return null;
    }

    public Object selectLikedStatisticsCross(String categoryOption, String startDate,
                                             String endDate, String specification) {
        return null;
    }

    public Object selectLikedStatisticsSeries(String categoryOption, String startDate,
                                              String endDate, String specification) {
        return null;
    }
}

