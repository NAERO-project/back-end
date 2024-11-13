package naero.naeroserver.order.service;

import naero.naeroserver.entity.order.TblPayment;
import naero.naeroserver.order.dto.OrderDTO;
import naero.naeroserver.order.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);
    private final PaymentRepository paymentRepository;

    private static final String API_KEY = "your-api-key";
    private static final String SECRET_KEY = "your-secret-key";

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // PortOne 결제 처리 메소드
    public String processPayment(OrderDTO orderDTO) {
        try {
            // Step 1: 토큰 발급
            String token = getAuthToken();
            if (token == null) {
                throw new RuntimeException("결제 인증 토큰 발급 실패");
            }

            // Step 2: 결제 요청
            ResponseEntity<String> response = sendPaymentRequest(token, orderDTO);
            if (response.getStatusCode() == HttpStatus.OK) {
                log.info("결제 요청 성공: {}", response.getBody());
                return "SUCCESS";
            } else {
                log.error("결제 요청 실패: {}", response.getBody());
                return "FAILURE";
            }
        } catch (Exception e) {
            log.error("결제 처리 중 오류 발생: {}", e.getMessage());
            return "FAILURE";
        }
    }

    // PortOne 토큰 발급 메소드
    private String getAuthToken() {
        try {
            String url = "https://api.portone.io/v1/auth/token";
            Map<String, String> credentials = new HashMap<>();
            credentials.put("api_key", API_KEY);
            credentials.put("api_secret", SECRET_KEY);

            ResponseEntity<Map> response = restTemplate.postForEntity(url, credentials, Map.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                Map<String, Object> responseBody = response.getBody();
                return responseBody.get("access_token").toString();
            }
            log.error("토큰 발급 실패: {}", response.getBody());
            return null;
        } catch (Exception e) {
            log.error("토큰 발급 오류: {}", e.getMessage());
            return null;
        }
    }

    // PortOne 결제 요청 메소드
    private ResponseEntity<String> sendPaymentRequest(String token, OrderDTO orderDTO) {
        String url = "https://api.portone.io/v1/payments";

        // 결제 요청 파라미터 설정
        Map<String, Object> paymentParams = new HashMap<>();
        paymentParams.put("amount", orderDTO.getOrderTotalAmount());
        paymentParams.put("order_id", orderDTO.getOrderId());
        paymentParams.put("payment_method", "kakaopay");  // 카카오톡 간편결제
        paymentParams.put("customer_email", orderDTO.getCustomerEmail());
        paymentParams.put("customer_name", orderDTO.getRecipientName());
        paymentParams.put("callback_url", "https://yourserver.com/api/payment/callback");

        // HTTP 요청 헤더에 토큰 추가
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        // 요청 전송
        return restTemplate.postForEntity(url, new HttpEntity<>(paymentParams, headers), String.class);
    }

    // 결제 정보를 데이터베이스에 저장
    public void savePayment(TblPayment payment) {
        paymentRepository.save(payment);
    }
}
