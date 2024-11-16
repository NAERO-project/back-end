package naero.naeroserver.monitoring.controller;

import io.swagger.v3.oas.annotations.Operation;
import naero.naeroserver.common.ResponseDTO;
import naero.naeroserver.monitoring.service.MonitoringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("/api")
public class MonitoringController {

    private static final Logger log = LoggerFactory.getLogger(MonitoringController.class);

    private final MonitoringService monitoringService;

    @Autowired
    public MonitoringController(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
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
    public ResponseEntity<ResponseDTO> selectSalesStatisticsCross(
            @RequestParam(defaultValue = "브랜드") String categoryOption,
            @RequestParam(defaultValue = "매출액") String indexOption,
            @RequestParam(required = false, defaultValue = "") String startDate,
            @RequestParam(required = false, defaultValue = "") String endDate,
            @RequestParam(required = false) String specification
    ) {

        // Log the input parameters for debugging
        log.info("Fetching sales statistics with categoryOption: {}, indexOption: {}, startDate: {}, endDate: {}, specification: {}",
                categoryOption, indexOption, startDate, endDate, specification);

        // Validate and parse dates
        LocalDate parsedStartDate = validateAndParseDate(startDate, LocalDate.now().minusWeeks(1));
        LocalDate parsedEndDate = validateAndParseDate(endDate, LocalDate.now());

        // Check date logic
        if (parsedStartDate.isAfter(parsedEndDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }
        if (parsedStartDate.isAfter(LocalDate.now()) || parsedEndDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Dates cannot be after today.");
        }

        // Check if specification input is null or not
        if (specification == null || specification.isEmpty()) {

            return ResponseEntity
                    .ok()
                    .body(new ResponseDTO(HttpStatus.OK, "횡단면 조회 성공",
                            monitoringService.selectSalesStatisticsCross(
                                    categoryOption, indexOption, startDate, endDate, specification)));
        } else {

            return ResponseEntity
                    .ok()
                    .body(new ResponseDTO(HttpStatus.OK, "시계열 조회 성공",
                            monitoringService.selectSalesStatisticsSeries(
                                    categoryOption, indexOption, startDate, endDate, specification)));
        }
    }

    /* 설명. 선호도 통계 데이터 조회 */
    @Operation(summary = "선호도 통계 조회 요청", description = "사용자 검색 입력에 따라 선호도 통계 조회가 진행됩니다.",
            tags = { "MonitoringController" })
    @GetMapping("/monitoring/liked-statistics")
    public ResponseEntity<ResponseDTO> selectLikedStatisticsCross(
            @RequestParam(defaultValue = "브랜드") String categoryOption,
//            @RequestParam String indexOption,   // 선호도는 찜 개수에 따라 집계하는 것이므로 지표선택은 적용되지 않음
            @RequestParam(required = false, defaultValue = "") String startDate,
            @RequestParam(required = false, defaultValue = "") String endDate,
            @RequestParam(required = false) String specification
    ) {

        // Validate and parse dates
        LocalDate parsedStartDate = validateAndParseDate(startDate, LocalDate.now().minusWeeks(1));
        LocalDate parsedEndDate = validateAndParseDate(endDate, LocalDate.now());

        // Check date logic
        if (parsedStartDate.isAfter(parsedEndDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }
        if (parsedStartDate.isAfter(LocalDate.now()) || parsedEndDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Dates cannot be after today.");
        }

        // Log the input parameters for debugging
        log.info("Fetching liked statistics with categoryOption: {}, startDate: {}, endDate: {}, specification: {}",
                categoryOption, startDate, endDate, specification);

        // Check if specification input is null or not
        if (specification == null || specification.isEmpty()) {

            return ResponseEntity
                    .ok()
                    .body(new ResponseDTO(HttpStatus.OK, "횡단면 조회 성공",
                            monitoringService.selectLikedStatisticsCross(
                                    categoryOption, startDate, endDate, specification)));
        } else {

            return ResponseEntity
                    .ok()
                    .body(new ResponseDTO(HttpStatus.OK, "시계열 조회 성공",
                            monitoringService.selectLikedStatisticsSeries(
                                    categoryOption, startDate, endDate, specification)));
        }
    }

    // Helper method to validate and parse dates
    private LocalDate validateAndParseDate(String date, LocalDate defaultDate) {
        try {
            return date.isEmpty() ? defaultDate : LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            log.error("Invalid date format: {}", date, e);
            throw new IllegalArgumentException("Invalid date format: " + date);
        }
    }
}