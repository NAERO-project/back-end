package naero.naeroserver.shipping.service;

import naero.naeroserver.entity.ship.TblShipping;
import naero.naeroserver.shipping.dto.TblShippingDTO;
import naero.naeroserver.shipping.repository.TblShippingRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ShippingService {

    /* Using a logging framework such as SLF4J with an implementation like Logback, Log4j,
     * or java.util.logging is preferred over using System.out.println() for several reasons:
     *  1. Log Levels
     *      - Logging Framework: Provides multiple logging levels (e.g., TRACE, DEBUG, INFO, WARN, ERROR),
     *        to control the verbosity of your logs and filter messages based on the severity level.
     *      - System.out.println(): Everything you print using System.out.println() is displayed at the
     *        same level, and there is no built-in mechanism to filter or categorize the output.
     *  2. Configurability and Flexibility
     *      - Logging Framework: You can change the logging output format/ write logs to different destinations
     *        such as files, databases, or external monitoring systems/ set up log rotation to avoid running out of space.
     *      - System.out.println(): You would need to write additional code to achieve similar functionality.
     *  3. Performance
     *      - Logging Framework: It can handle logging asynchronously and buffer messages, reducing the performance impact.
     *      - System.out.println(): Synchronous and can significantly impact performance if used excessively.
     *  4. Centralized Logging Configuration
     *      - Logging Framework: Spring Boot allows you to configure logging settings centrally through a configuration
     *        file (e.g., application.properties, logback.xml, or log4j2.xml). This makes it easy to manage and update
     *        logging behavior across your entire application.
     *      - System.out.println(): There is no centralized way to control or configure how messages are logged.
     *  5. Structure Logging and Contextual Information
     *  6. Integration with Monitoring and Analysis Tools
     *      - Logging Framework: Can be integrated with log aggregation and monitoring tools like ELK Stack (Elasticsearch,
     *        Logstash, and Kibana), Splunk, or AWS CloudWatch. This enables you to collect, analyze, and visualize log data
     *        from multiple sources in real-time.
     *      - System.out.println(): Not easily integrated with these tools, and you would need extra effort to parse and
     *        manage console output.
     *  7. Better Handling of Exceptions
     *      - Logging Framework: Provides methods for logging exceptions, including stack traces, in a structured way
     *      - System.out.println(): If you use System.out.println(e), you won't get the full stack trace, making debugging more difficult.
     *  8. Asynchronous Logging
     *  9. Standardized Logging Practices
     *  */
    private static final Logger log = LoggerFactory.getLogger(ShippingService.class);
    private final TblShippingRepository tblShippingRepository;
    private final ModelMapper modelMapper;

    /* 설명. 스마트택배 API 관련 필드 */
    @Value("${sweettracker.api.base-url}")
    private String baseUrl;
    @Value("${sweettracker.api.key}")
    private String apiKey;

    @Autowired
    public ShippingService(TblShippingRepository tblShippingRepository, ModelMapper modelMapper) {
        this.tblShippingRepository = tblShippingRepository;
        this.modelMapper = modelMapper;
    }

    public Object selectShipping(String trackingNumber) {
        log.info("[ShippingService] selectShippingList() Start");

        /* 설명. 메서드 이름은 반드시 레포지터리의 콜럼명이랑 일치해야 함. 아니면 에러 발생 */
        TblShipping shippingDetail = tblShippingRepository.findByTrackingNumber(trackingNumber);

        log.info("[ShippingService] shippingList {}", shippingDetail);
        log.info("[ShippingService] selectShippingList() End");

        return modelMapper.map(shippingDetail, TblShippingDTO.class);
    }

    public Object getTrackingInformation(String trackingNumber, String shipComCode) {
        // Initialize RestClient
        RestClient restClient = RestClient.builder().build();

        // Construct the API URL
        String url = baseUrl + "/api/v1/trackingInfo?t_key=" + apiKey
                        + "&t_code=" + shipComCode + "&t_invoice=" + trackingNumber;

        // Make the API request using RestClient
        return restClient.get()
                .uri(url)
                .retrieve()
                .body(Map.class);
    }

    @Transactional
    public Object updateShipping(TblShippingDTO tblShippingDTO) {
        log.info("[ShippingService] updateShipping() Start");
        log.info("[ShippingService] tblShippingDTO {}", tblShippingDTO);

        /* 설명. 초기 반환값 0으로 설정 */
        int result = 0;

        try {
            /* 설명. update 할 엔티티 조회 */
            TblShipping tblShipping = tblShippingRepository.findById(tblShippingDTO.getShippingId()).get();

            /* 설명. update를 위한 엔티티 값 수정 */
            tblShipping.setId(tblShippingDTO.getShippingId());
            tblShipping.setTrackingNumber(tblShippingDTO.getTrackingNumber());
            tblShipping.setShippingStatus(tblShippingDTO.getShippingStatus());
            tblShipping.setOrderId(tblShippingDTO.getOrderId());
            tblShipping.setShipCom(tblShipping.getShipCom());

            result = 1;

        } catch (Exception e) {
            log.error("[ShippingService] Exception!");
        }

        log.info("[ShippingService] updateShipping() End =============]");

        return (result > 0) ? "배송 업데이트 성공" : "배송 업데이트 실패";
    }

}
