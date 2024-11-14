package naero.naeroserver.order.service;

import naero.naeroserver.entity.order.TblOrder;
import naero.naeroserver.entity.order.TblOrderDetail;
import naero.naeroserver.entity.order.TblPayment;
import naero.naeroserver.entity.product.TblOption;
import naero.naeroserver.entity.product.TblProduct;
import naero.naeroserver.entity.user.TblUser;
import naero.naeroserver.member.repository.UserRepository;
import naero.naeroserver.order.dto.OrderDTO;
import naero.naeroserver.order.dto.OrderDetailDTO;
import naero.naeroserver.order.dto.PaymentDTO;
import naero.naeroserver.order.repository.OrderDetailRepository;
import naero.naeroserver.order.repository.OrderRepository;
import naero.naeroserver.order.repository.PaymentRepository;
import naero.naeroserver.product.repository.OptionRepository;
import naero.naeroserver.product.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
//    private final PaymentService paymentService;
    private final OrderDetailRepository orderDetailRepository;
    private final PaymentRepository paymentRepository;
    private final OptionRepository optionRepository;

    @Value("${portone.api-key}")
    private String API_KEY;
    @Value("${portone.api-secret}")
    private String API_SECRET;
    private static final long TOKEN_EXPIRY_DURATION = 3600 * 1000; // 1시간

    private final AtomicReference<String> cachedToken = new AtomicReference<>();
    private long tokenFetchTime = 0;

    private final WebClient webClient = WebClient.builder().baseUrl("https://api.portone.io").build();

    @Autowired
    public OrderService(ModelMapper modelMapper, OrderRepository orderRepository, UserRepository userRepository,
                        ProductRepository productRepository, OrderDetailRepository orderDetailRepository, PaymentRepository paymentRepository, OptionRepository optionRepository) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.paymentRepository = paymentRepository;
        this.optionRepository = optionRepository;
    }

    @Transactional
    public Object insertOrder(OrderDTO orderDTO, PaymentDTO paymentDTO, int productId) {
        log.info("[OrderService] insertOrder() 시작");
        log.info("[OrderService] orderDTO : {}", orderDTO);

        try {
            // 1. 결제 정보 검증
            System.out.println("portoneToken() 시작");
            String portoneToken = getPortOneToken();
            log.info(portoneToken);
//            Map<String, Object> paymentInfo = validatePayment(paymentDTO.getImp_uid(), orderDTO.getOrderTotalAmount(), portoneToken);
            // 결제 검증 후 응답에서 필요한 정보 추가
//            paymentDTO.setCurrency((String) paymentInfo.get("currency"));
            paymentDTO.setCurrency("KRW");
            paymentDTO.setPaymentStatus("completed"); // 예: 결제 완료로 설정
//            paymentDTO.setTransaction_id((String) paymentInfo.get("transaction_id"));
            paymentDTO.setTransaction_id("transid_1223423423422");
//            paymentDTO.setReceipt_url((String) paymentInfo.get("receipt_url"));
            paymentDTO.setReceipt_url("sample_url");

            TblUser user = userRepository.findTblUserById(orderDTO.getUserId());

            // 2. 주문 정보 저장
            TblOrder order = new TblOrder();
            order.setUser(user);
            order.setOrderDatetime(Instant.now());
            order.setOrderTotalAmount(orderDTO.getOrderTotalAmount());
            order.setOrderTotalCount(orderDTO.getOrderTotalCount());
            order.setDeliveryStatus(orderDTO.getDeliveryStatus());
            order.setOrderStatus(orderDTO.getOrderStatus());
            order.setDeliveryFee(orderDTO.getDeliveryFee());
            order.setDiscountAmount(orderDTO.getDiscountAmount());
            order.setRecipientName(orderDTO.getRecipientName());
            order.setRecipientPhoneNumber(orderDTO.getRecipientPhoneNumber());
            order.setPostalCode(orderDTO.getPostalCode());
            order.setAddressRoad(orderDTO.getAddressRoad());
            order.setAddressDetail(orderDTO.getAddressDetail());
            order.setAddressName(orderDTO.getAddressName());
            order.setDeliveryNote(orderDTO.getDeliveryNote());

            log.info(String.valueOf(orderDTO));
//            TblOrder order = modelMapper.map(orderDTO, TblOrder.class);
//            order.setUser(user);
//            order.setOrderDatetime(Instant.now());

            orderRepository.save(order);

            // 3. 결제 정보 저장
            TblPayment payment = new TblPayment();
            payment.setUserId(paymentDTO.getUserId());
            payment.setOrder(order); // 저장된 order의 ID 참조
            payment.setAmount(orderDTO.getOrderTotalAmount());
            payment.setCurrency(paymentDTO.getCurrency());
            payment.setPaymentMethod(paymentDTO.getPaymentMethod());
            payment.setPaymentStatus(paymentDTO.getPaymentStatus());
            payment.setImpUid(paymentDTO.getImp_uid());
            payment.setMerchantUid(paymentDTO.getMerchant_uid());
            payment.setReceiptUrl(paymentDTO.getReceipt_url());
            payment.setTransactionId(paymentDTO.getTransaction_id());

            paymentRepository.save(payment);

            TblProduct product = productRepository.findByProductId(productId);
            TblOption option = optionRepository.findByProduct(product);
            if (option.getOptionId() == null) {
                throw new IllegalStateException("해당 상품에 대한 옵션이 존재하지 않습니다.");
            }

            // 4. 주문 상세 정보 저장
            TblOrderDetail orderDetail = new TblOrderDetail();
            orderDetail.setAmount(orderDTO.getDiscountAmount());
            orderDetail.setCount(orderDTO.getOrderTotalCount());
            orderDetail.setOrder(order);
            orderDetail.setOptionId(option.getOptionId());

            orderDetailRepository.save(orderDetail);

            return modelMapper.map(order, OrderDTO.class);
        } catch (Exception e) {
            log.error("[OrderService] Exception: {}", e.getMessage());
            return null;
        }
    }

    // 결제 검증 메서드
//    public Mono<Map> validatePaymentAsync(String impUid, int orderTotalAmount, String portoneToken) {
//        String url = "/v2/payments/" + impUid;
//
//        return webClient.get()
//                .uri(url)
//                .header(HttpHeaders.AUTHORIZATION, portoneToken)
//                .retrieve()
//                .bodyToMono(Map.class)
//                .doOnSuccess(paymentData -> {
//                    // 응답 후 로직 처리
//                    int amount = (int) paymentData.get("amount");
//                    if (orderTotalAmount != amount) {
//                        throw new IllegalStateException("결제 금액 불일치");
//                    }
//                });
//    }
    private Map<String, Object> validatePayment(String impUid, int orderTotalAmount, String portoneToken) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.portone.io/v2/payments/" + impUid;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", portoneToken);

        ResponseEntity<Map> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                Map.class
        );

        Map<String, Object> paymentData = response.getBody();
        int amount = (int) paymentData.get("amount");
        if (orderTotalAmount != amount) {
            throw new IllegalStateException("결제 금액 불일치");
        }

        return paymentData;
    }

    // 포트원 토큰 요청 메서드
//    private String getPortOneToken() {
//        if (isTokenValid()) {
//            return cachedToken.get();
//        }
//
//        String newToken = requestNewToken();
//        cachedToken.set(newToken);
//        tokenFetchTime = System.currentTimeMillis();
//        return newToken;
//    }
//
//    private boolean isTokenValid() {
//        return cachedToken.get() != null && (System.currentTimeMillis() - tokenFetchTime < TOKEN_EXPIRY_DURATION);
//    }
//
//    private String requestNewToken() {
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
//        body.add("api_key", API_KEY);
//        body.add("api_secret", API_SECRET);
//
//        try {
//            ResponseEntity<Map> response = restTemplate.postForEntity(
//                    API_URL,
//                    new HttpEntity<>(body, headers),
//                    Map.class
//            );
//
//            Map<String, Object> responseBody = response.getBody();
//            if (responseBody == null || !responseBody.containsKey("access_token")) {
//                throw new IllegalStateException("토큰 응답이 유효하지 않습니다: " + responseBody);
//            }
//
//            return "Bearer " + responseBody.get("access_token").toString();
//        } catch (Exception e) {
//            throw new IllegalStateException("포트원 토큰 요청 실패", e);
//        }
//    }
    private String getPortOneToken() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.portone.io/login/api-secret";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
//        body.put("api_key", API_KEY);
        body.put("apiSecret", API_SECRET);

        ResponseEntity<Map> response = restTemplate.postForEntity(
                url,
                new HttpEntity<>(body, headers),
                Map.class
        );

        return "Bearer " + response.getBody().get("accessToken").toString();
    }

    public Object selectOrderList(String userId) {
        TblUser user = userRepository.findTblUserById(Integer.parseInt(userId));
        List<TblOrder> orderList = orderRepository.findByUser(user);

        log.info("[OrderService] orderList {}", orderList);

        return orderList.stream().map(order -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toList());

    }

    public Object selectOrderDetailList(String orderId) {
        TblOrder order = orderRepository.findTblOrderById(Integer.valueOf(orderId));
        List<TblOrderDetail> orderDetails = orderDetailRepository.findAllByOrder(order);

        log.info("[OrderService] orderDetails {}", orderDetails);

        return orderDetails.stream().map(orderDetail -> modelMapper.map(orderDetail, OrderDetailDTO.class)).collect(Collectors.toList());
    }
}
