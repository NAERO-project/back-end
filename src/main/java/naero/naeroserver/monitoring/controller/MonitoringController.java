package naero.naeroserver.monitoring.controller;

import io.swagger.v3.oas.annotations.Operation;
import naero.naeroserver.common.ResponseDTO;
import naero.naeroserver.monitoring.service.MonitoringService;
import naero.naeroserver.monitoring.service.MonitoringServiceStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MonitoringController {

    private static final Logger log = LoggerFactory.getLogger(MonitoringController.class);

    private final MonitoringService monitoringService;
    private final MonitoringServiceStat monitoringServiceStat;

    @Autowired
    public MonitoringController(MonitoringService monitoringService, MonitoringServiceStat monitoringServiceStat) {
        this.monitoringService = monitoringService;
        this.monitoringServiceStat = monitoringServiceStat;
    }

    /* 설명. 24시간 매출 총액 데이터 조회 */
    @Operation(summary = "24시간 매출 총액 조회 요청", description = "페이지가 로딩되면 매출 총액 조회가 진행됩니다.",
            tags = { "MonitoringController" })
    @GetMapping("/monitoring/today-sales")
    public ResponseEntity<ResponseDTO> selectTodaySalesAmount() {

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "조회 성공",
                        monitoringService.selectTodaySalesAmount()));
    }

    /* 설명. 24시간 매출 수량 데이터 조회 */
    @Operation(summary = "24시간 매출 수량 조회 요청", description = "페이지가 로딩되면 매출 수량 조회가 진행됩니다.",
            tags = { "MonitoringController" })
    @GetMapping("/monitoring/today-quantity")
    public ResponseEntity<ResponseDTO> selectTodaySalesQuantity() {

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "조회 성공",
                        monitoringService.selectTodaySalesQuantity()));
    }

    /* 설명. 24시간 판매 상품 종류 데이터 조회 */
    @Operation(summary = "24시간 판매 상품 종류 조회 요청", description = "페이지가 로딩되면 판매 상품 종류 조회가 진행됩니다.",
            tags = { "MonitoringController" })
    @GetMapping("/monitoring/today-items")
    public ResponseEntity<ResponseDTO> selectTodaySalesItems() {

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "조회 성공",
                        monitoringService.selectTodaySalesItems()));
    }

    /* 설명. 24시간 신규 회원 데이터 조회 */
    @Operation(summary = "24시간 신규 회원 조회 요청", description = "페이지가 로딩되면 신규 회원 조회가 진행됩니다.",
            tags = { "MonitoringController" })
    @GetMapping("/monitoring/today-members")
    public ResponseEntity<ResponseDTO> selectTodayNewMembers() {

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "조회 성공",
                        monitoringService.selectTodayNewMembers()));
    }

    /* 설명. 매출 통계 데이터 조회 */
    @Operation(summary = "매출 통계 조회 요청", description = "사용자 검색 입력에 따라 매출 통계 조회가 진행됩니다.",
            tags = { "MonitoringController" })
    @GetMapping("/monitoring/sales-statistics")
    public ResponseEntity<ResponseDTO> selectSalesStatistics(
            @RequestParam(defaultValue = "상품") String categoryOption,
            @RequestParam(defaultValue = "매출액") String indexOption,
            @RequestParam(required = false, defaultValue = "") String startDate,
            @RequestParam(required = false, defaultValue = "") String endDate,
            @RequestParam(required = false) String specification
    ) {
        return getStatisticsResponse(categoryOption, indexOption, startDate, endDate, specification, true);
    }

    /* 설명. 선호도 통계 데이터 조회 */
    @Operation(summary = "선호도 통계 조회 요청", description = "사용자 검색 입력에 따라 선호도 통계 조회가 진행됩니다.",
            tags = { "MonitoringController" })
    @GetMapping("/monitoring/liked-statistics")
    public ResponseEntity<ResponseDTO> selectLikedStatisticsCross(
            @RequestParam(defaultValue = "상품") String categoryOption,
            @RequestParam(required = false, defaultValue = "") String startDate,
            @RequestParam(required = false, defaultValue = "") String endDate,
            @RequestParam(required = false) String specification
    ) {
        return getStatisticsResponse(categoryOption, null, startDate, endDate, specification, false);
    }

    // Modularized method for handling the response
    private ResponseEntity<ResponseDTO> getStatisticsResponse(String categoryOption, String indexOption,
                                                              String startDate, String endDate, String specification,
                                                              boolean isSalesStatistics) {
        // Log the input parameters
        log.info("[MonitoringController] Fetching statistics with categoryOption: {}, indexOption: {}, startDate: {}, endDate: {}, specification: {}",
                categoryOption, indexOption, startDate, endDate, specification);

        // Validate and parse dates to Instant
        Instant parsedStartInstant = validateAndParseDateTime(startDate, LocalDateTime.now().minusWeeks(1));
        Instant parsedEndInstant = validateAndParseDateTime(endDate, LocalDateTime.now());

        System.out.println("[날자확인] parsedStartInstant = " + parsedStartInstant);
        System.out.println("[날자확인] parsedEndInstant = " + parsedEndInstant);

        // Combined date validation logic
        if (parsedStartInstant.isAfter(parsedEndInstant) || parsedEndInstant.isAfter(Instant.now())) {
            throw new IllegalArgumentException("Invalid date range: Dates cannot be after today, and the start date cannot be after the end date.");
        }

        // Call the appropriate service method based on specification
        List<Map<String, Object>> response = (specification == null || specification.trim().isEmpty())
                ? (isSalesStatistics
                ? monitoringServiceStat.selectSalesStatisticsCross(categoryOption, indexOption, parsedStartInstant, parsedEndInstant)
                : monitoringServiceStat.selectLikedStatisticsCross(categoryOption, parsedStartInstant, parsedEndInstant))
                : (isSalesStatistics
                ? monitoringServiceStat.selectSalesStatisticsSeries(categoryOption, indexOption, parsedStartInstant, parsedEndInstant, specification)
                : monitoringServiceStat.selectLikedStatisticsSeries(categoryOption, parsedStartInstant, parsedEndInstant, specification));

        String message = (specification == null || specification.trim().isEmpty()) ? "횡단면 조회 성공" : "시계열 조회 성공";
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, message, response));
    }

    // Helper method to validate and parse date-times to Instant
    private Instant validateAndParseDateTime(String dateTime, LocalDateTime defaultDateTime) {
        try {
            LocalDateTime localDateTime = dateTime.isEmpty()
                    ? defaultDateTime
                    : LocalDateTime.parse(dateTime.contains("T") ? dateTime : dateTime + "T00:00:00");
            return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        } catch (DateTimeParseException e) {
            log.error("Invalid date-time format: {}", dateTime, e);
            throw new IllegalArgumentException("Invalid date-time format: " + dateTime);
        }
    }
}